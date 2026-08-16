// ── Constants ──────────────────────────────────────────────────────────────
const COLORS = [
  '#e74c3c','#3498db','#2ecc71','#f39c12',
  '#9b59b6','#1abc9c','#e91e8c','#f1c40f',
];
const BASE_SIZE  = 5;
const SIZE_STEP  = 2;
function boardSizeForRound(r) { return BASE_SIZE + (r - 1) * SIZE_STEP; }

// Timer shrinks each round: 30s → 25s → 20s → 15s → 10s (floor at 10)
const BASE_TIME  = 30;
const TIME_STEP  = 5;
function turnTimeForRound(r) { return Math.max(10, BASE_TIME - (r - 1) * TIME_STEP); }

// ── State ──────────────────────────────────────────────────────────────────
let G = {
  mode: 'pvp', totalRounds: 3, boardSize: 5,
  currentRound: 1, scores: [0,0], colors: [COLORS[0], COLORS[1]],
  board: [], positions: [null,null], currentPlayer: 0,
  selectedCell: null, highlights: [],
  timerInterval: null, timeLeft: [30, 30],
  roundLog: [], gameOver: false, streak: [0,0],
};

// ── Particles ──────────────────────────────────────────────────────────────
(function initParticles() {
  const canvas = document.getElementById('particles');
  const ctx = canvas.getContext('2d');
  let W, H, dots = [];
  function resize() { W = canvas.width = window.innerWidth; H = canvas.height = window.innerHeight; }
  function spawn() {
    dots = Array.from({length:55}, () => ({
      x: Math.random()*W, y: Math.random()*H,
      r: Math.random()*1.4+0.3,
      vx: (Math.random()-0.5)*0.25, vy: (Math.random()-0.5)*0.25,
      a: Math.random()*0.35+0.05,
    }));
  }
  function draw() {
    ctx.clearRect(0,0,W,H);
    dots.forEach(d => {
      d.x+=d.vx; d.y+=d.vy;
      if(d.x<0)d.x=W; if(d.x>W)d.x=0;
      if(d.y<0)d.y=H; if(d.y>H)d.y=0;
      ctx.beginPath(); ctx.arc(d.x,d.y,d.r,0,Math.PI*2);
      ctx.fillStyle=`rgba(124,111,255,${d.a})`; ctx.fill();
    });
    requestAnimationFrame(draw);
  }
  window.addEventListener('resize',()=>{resize();spawn();});
  resize(); spawn(); draw();
})();

// ── Screen helpers ─────────────────────────────────────────────────────────
function showScreen(id) {
  document.querySelectorAll('.screen').forEach(s=>s.classList.remove('active'));
  document.getElementById(id).classList.add('active');
}
function exitGame() { if(confirm('Exit the game?')) window.close(); }
function confirmQuit() { if(confirm('Quit to main menu?')) { stopTimer(); showScreen('screen-menu'); } }

// ── Mode / Setup ───────────────────────────────────────────────────────────
function selectMode(mode) { G.mode=mode; buildSetupScreen(); showScreen('screen-setup'); }

function buildSetupScreen() {
  document.getElementById('rounds-display').textContent = G.totalRounds;
  document.getElementById('p2-label').textContent = G.mode==='ai' ? 'AI' : 'Player 2';
  updateRoundPreview();
  buildColorPicker('p1-colors',0);
  buildColorPicker('p2-colors',1);
  updatePreviews();
}

function updateRoundPreview() {
  const el = document.getElementById('round-preview');
  if(!el) return;
  el.textContent = Array.from({length:G.totalRounds},(_,i)=>{
    const s=boardSizeForRound(i+1);
    const t=turnTimeForRound(i+1);
    return `R${i+1}: ${s}×${s} ${t}s`;
  }).join('  →  ');
}

function buildColorPicker(cid, pi) {
  const c = document.getElementById(cid); c.innerHTML='';
  const other = G.colors[1-pi];
  COLORS.forEach(color => {
    const sw = document.createElement('div');
    sw.className='color-swatch';
    sw.style.background=color;
    if(color===G.colors[pi]) sw.classList.add('selected');
    if(color===other) sw.classList.add('disabled');
    sw.onclick=()=>{
      if(color===G.colors[1-pi]) return;
      G.colors[pi]=color;
      buildColorPicker(cid,pi);
      buildColorPicker(pi===0?'p2-colors':'p1-colors',1-pi);
      updatePreviews();
    };
    c.appendChild(sw);
  });
}

function updatePreviews() {
  document.getElementById('p1-preview').style.background=G.colors[0];
  document.getElementById('p2-preview').style.background=G.colors[1];
}

function changeRounds(delta) {
  let r=G.totalRounds+delta;
  if(r<1)r=1; if(r>9)r=9;
  if(r%2===0) r=Math.max(1,r+(delta>0?1:-1));
  G.totalRounds=r;
  document.getElementById('rounds-display').textContent=G.totalRounds;
  updateRoundPreview();
}

// ── Game start ─────────────────────────────────────────────────────────────
function startGame() {
  G.scores=[0,0]; G.currentRound=1; G.roundLog=[]; G.streak=[0,0];
  document.getElementById('total-rounds').textContent=G.totalRounds;
  document.getElementById('p1-name').textContent='Player 1';
  document.getElementById('p2-name').textContent=G.mode==='ai'?'AI':'Player 2';
  ['p1-dot','p2-dot'].forEach((id,i)=>{
    const el=document.getElementById(id);
    el.style.background=G.colors[i];
    el.style.boxShadow=`0 0 12px ${G.colors[i]}`;
  });
  showScreen('screen-game');
  startRound();
}

function startRound() {
  stopTimer();
  const S=boardSizeForRound(G.currentRound);
  G.boardSize=S;
  G.board=Array.from({length:S},()=>Array(S).fill('empty'));
  G.positions[0]={r:0,c:0};
  G.positions[1]={r:S-1,c:S-1};
  G.board[0][0]='p1';
  G.board[S-1][S-1]='p2';
  G.currentPlayer=0;
  G.selectedCell=null; G.highlights=[]; G.gameOver=false;
  const roundTime = turnTimeForRound(G.currentRound);
  G.timeLeft=[roundTime, roundTime];
  document.getElementById('current-round').textContent=G.currentRound;
  document.getElementById('round-size-hint').textContent=`${S}×${S} · ${roundTime}s`;
  updateScoreDots();
  renderBoard();
  updateActivePlayer();
  updateTurnBanner();
  startTimer();
}

// ── Board render ───────────────────────────────────────────────────────────
function cellSize() {
  const S=G.boardSize;
  return S<=5?62:S<=7?54:S<=9?44:36;
}

function renderBoard() {
  const S=G.boardSize;
  const cs=cellSize();
  const ms=Math.round(cs*0.62);
  const ds=Math.round(cs*0.26);
  const boardEl=document.getElementById('board');
  boardEl.style.gridTemplateColumns=`repeat(${S},${cs}px)`;
  boardEl.innerHTML='';

  // danger: count moves for each player
  const moves0=getValidMoves(G.positions[0].r,G.positions[0].c).length;
  const moves1=getValidMoves(G.positions[1].r,G.positions[1].c).length;

  for(let r=0;r<S;r++) {
    for(let c=0;c<S;c++) {
      const cell=document.createElement('div');
      cell.className='cell';
      cell.style.width=cs+'px'; cell.style.height=cs+'px';
      const val=G.board[r][c];

      if(val==='removed') {
        cell.classList.add('removed');
      } else if(val==='p1'||val==='p2') {
        cell.classList.add('piece');
        const marker=document.createElement('div');
        marker.className='piece-marker';
        marker.id=`marker-${r}-${c}`;
        marker.style.width=ms+'px'; marker.style.height=ms+'px';
        const col=val==='p1'?G.colors[0]:G.colors[1];
        marker.style.background=col;
        marker.style.boxShadow=`0 0 14px ${col}99`;
        cell.appendChild(marker);
        if(G.selectedCell&&G.selectedCell.r===r&&G.selectedCell.c===c)
          cell.classList.add('selected-piece');
        // danger glow on piece if nearly trapped
        const pi=val==='p1'?0:1;
        const mv=pi===0?moves0:moves1;
        if(mv<=2&&mv>0) cell.classList.add('danger-piece');
        if(mv===0) cell.classList.add('trapped-piece');
      }

      if(G.highlights.some(h=>h.r===r&&h.c===c)) {
        cell.classList.add('highlight');
        cell.style.setProperty('--dot-sz',ds+'px');
      }

      cell.dataset.r=r; cell.dataset.c=c;
      cell.onclick=()=>handleCellClick(r,c);
      boardEl.appendChild(cell);
    }
  }
}

// ── Smooth slide animation ─────────────────────────────────────────────────
function animateMove(fromR, fromC, toR, toC, color, onDone) {
  const boardEl = document.getElementById('board');
  const cs = cellSize();
  const gap = 3;
  const step = cs + gap;

  // pixel offsets of from-cell relative to board
  const fromX = fromC * step;
  const fromY = fromR * step;
  const toX   = toC   * step;
  const toY   = toR   * step;

  const ms = Math.round(cs * 0.62);

  // create floating marker
  const fly = document.createElement('div');
  fly.className = 'fly-marker';
  fly.style.cssText = `
    width:${ms}px; height:${ms}px;
    background:${color};
    box-shadow: 0 0 18px ${color}cc;
    border-radius:50%;
    border:3px solid #fff4;
    position:absolute;
    left:${fromX + (cs-ms)/2 + 8}px;
    top:${fromY  + (cs-ms)/2 + 8}px;
    transition: left 0.22s cubic-bezier(.4,0,.2,1), top 0.22s cubic-bezier(.4,0,.2,1);
    pointer-events:none;
    z-index:10;
  `;
  boardEl.style.position='relative';
  boardEl.appendChild(fly);

  // trigger transition on next frame
  requestAnimationFrame(()=>{
    requestAnimationFrame(()=>{
      fly.style.left = `${toX + (cs-ms)/2 + 8}px`;
      fly.style.top  = `${toY + (cs-ms)/2 + 8}px`;
    });
  });

  fly.addEventListener('transitionend', ()=>{
    fly.remove();
    onDone();
  }, {once:true});
}

// ── Input ──────────────────────────────────────────────────────────────────
function handleCellClick(r,c) {
  if(G.gameOver) return;
  if(G.mode==='ai'&&G.currentPlayer===1) return;
  const val=G.board[r][c];
  const cp=G.currentPlayer;
  const myPiece=cp===0?'p1':'p2';

  if(val===myPiece) {
    G.selectedCell={r,c};
    G.highlights=getValidMoves(r,c);
    renderBoard();
    setStatus(G.highlights.length?'Choose where to move':'No moves — you lose!');
  } else if(G.selectedCell&&G.highlights.some(h=>h.r===r&&h.c===c)) {
    doMove(G.selectedCell.r, G.selectedCell.c, r, c);
  } else {
    G.selectedCell=null; G.highlights=[];
    renderBoard();
    setStatus('');
  }
}

function doMove(fromR, fromC, toR, toC) {
  const cp=G.currentPlayer;
  const myPiece=cp===0?'p1':'p2';
  const color=G.colors[cp];

  // hide the piece on board during animation
  G.selectedCell=null; G.highlights=[];
  renderBoard();

  // temporarily blank the from-cell marker so fly-marker is the only one
  const fromCell=document.querySelector(`[data-r="${fromR}"][data-c="${fromC}"]`);
  if(fromCell) { const m=fromCell.querySelector('.piece-marker'); if(m) m.style.opacity='0'; }

  animateMove(fromR, fromC, toR, toC, color, ()=>{
    executeMove(fromR, fromC, toR, toC);
  });
}

function executeMove(fromR, fromC, toR, toC) {
  const cp=G.currentPlayer;
  const myPiece=cp===0?'p1':'p2';

  // crack animation on removed tile
  G.board[fromR][fromC]='removed';
  G.board[toR][toC]=myPiece;
  G.positions[cp]={r:toR,c:toC};

  renderBoard();
  flashCell(fromR, fromC, 'crack-flash');

  const next=1-cp;
  const nextMoves=getValidMoves(G.positions[next].r, G.positions[next].c);

  if(nextMoves.length===0) {
    // also check if current player is also stuck (shouldn't happen but safety)
    endRound(cp);
    return;
  }

  G.currentPlayer=next;
  G.timeLeft[next]=turnTimeForRound(G.currentRound);
  updateActivePlayer();
  updateTurnBanner();
  renderBoard();
  setStatus('');

  if(G.mode==='ai'&&next===1) setTimeout(aiMove,750);
}

function flashCell(r,c,cls) {
  const cell=document.querySelector(`[data-r="${r}"][data-c="${c}"]`);
  if(!cell) return;
  cell.classList.add(cls);
  setTimeout(()=>cell.classList.remove(cls),500);
}

// ── Valid moves ────────────────────────────────────────────────────────────
function getValidMoves(r,c) {
  const dirs=[[-1,0],[1,0],[0,-1],[0,1]];
  const S=G.boardSize; const moves=[];
  for(const [dr,dc] of dirs) {
    const nr=r+dr, nc=c+dc;
    if(nr>=0&&nr<S&&nc>=0&&nc<S&&G.board[nr][nc]==='empty')
      moves.push({r:nr,c:nc});
  }
  return moves;
}

// ── AI — Minimax with Alpha-Beta Pruning ──────────────────────────────────
const AI_DEPTH = 5; // lookahead depth (increase for harder AI, decrease for speed)

function aiMove() {
  if(G.gameOver) return;
  const pos = G.positions[1];
  const moves = getValidMoves(pos.r, pos.c);
  if(!moves.length) { endRound(0); return; }

  let bestMove = moves[0];
  let bestScore = -Infinity;

  for(const mv of moves) {
    // apply move
    const prevBoard = G.board[pos.r][pos.c];
    G.board[pos.r][pos.c] = 'removed';
    G.board[mv.r][mv.c] = 'p2';
    G.positions[1] = mv;

    const score = alphaBeta(AI_DEPTH - 1, -Infinity, Infinity, false);

    // undo move
    G.board[pos.r][pos.c] = prevBoard;
    G.board[mv.r][mv.c] = 'empty';
    G.positions[1] = pos;

    if(score > bestScore) { bestScore = score; bestMove = mv; }
  }

  G.selectedCell = pos;
  G.highlights = [bestMove];
  renderBoard();
  setTimeout(() => doMove(pos.r, pos.c, bestMove.r, bestMove.c), 350);
}

// Returns score from AI (p2) perspective: positive = good for AI
function alphaBeta(depth, alpha, beta, isMaximizing) {
  const aiPos  = G.positions[1];
  const oppPos = G.positions[0];
  const aiMoves  = getValidMoves(aiPos.r,  aiPos.c);
  const oppMoves = getValidMoves(oppPos.r, oppPos.c);

  // Terminal: current mover has no moves → they lose
  if(isMaximizing && aiMoves.length === 0)  return -1000 - depth;
  if(!isMaximizing && oppMoves.length === 0) return  1000 + depth;

  if(depth === 0) return evaluate();

  if(isMaximizing) {
    let best = -Infinity;
    for(const mv of aiMoves) {
      const prev = G.board[aiPos.r][aiPos.c];
      G.board[aiPos.r][aiPos.c] = 'removed';
      G.board[mv.r][mv.c] = 'p2';
      G.positions[1] = mv;

      best = Math.max(best, alphaBeta(depth - 1, alpha, beta, false));

      G.board[aiPos.r][aiPos.c] = prev;
      G.board[mv.r][mv.c] = 'empty';
      G.positions[1] = aiPos;

      alpha = Math.max(alpha, best);
      if(beta <= alpha) break; // prune
    }
    return best;
  } else {
    let best = Infinity;
    for(const mv of oppMoves) {
      const prev = G.board[oppPos.r][oppPos.c];
      G.board[oppPos.r][oppPos.c] = 'removed';
      G.board[mv.r][mv.c] = 'p1';
      G.positions[0] = mv;

      best = Math.min(best, alphaBeta(depth - 1, alpha, beta, true));

      G.board[oppPos.r][oppPos.c] = prev;
      G.board[mv.r][mv.c] = 'empty';
      G.positions[0] = oppPos;

      beta = Math.min(beta, best);
      if(beta <= alpha) break; // prune
    }
    return best;
  }
}

// Heuristic: AI moves - opponent moves, weighted by board position
function evaluate() {
  const aiPos  = G.positions[1];
  const oppPos = G.positions[0];
  const aiMoves  = getValidMoves(aiPos.r,  aiPos.c).length;
  const oppMoves = getValidMoves(oppPos.r, oppPos.c).length;
  // Prefer center positions (more future mobility)
  const S = G.boardSize;
  const center = (S - 1) / 2;
  const aiCenter  = -(Math.abs(aiPos.r  - center) + Math.abs(aiPos.c  - center));
  const oppCenter = -(Math.abs(oppPos.r - center) + Math.abs(oppPos.c - center));
  return (aiMoves - oppMoves) * 10 + (aiCenter - oppCenter);
}

// ── Round end ──────────────────────────────────────────────────────────────
function endRound(winner) {
  stopTimer(); G.gameOver=true;
  G.scores[winner]++;
  G.streak[winner]++;
  G.streak[1-winner]=0;
  const wName=winner===0?'Player 1':(G.mode==='ai'?'AI':'Player 2');
  const streak=G.streak[winner];
  G.roundLog.push({round:G.currentRound,winner:wName,streak});
  updateScoreDots();
  const streakMsg=streak>=2?` 🔥 ${streak} in a row!`:'';
  setStatus(`🏆 ${wName} wins!${streakMsg}`);
  showWinFlash(winner);
  renderBoard();

  setTimeout(()=>{
    if(G.currentRound>=G.totalRounds) showResult();
    else { G.currentRound++; startRound(); }
  }, 2400);
}

function showWinFlash(winner) {
  const card=document.getElementById(`p${winner+1}-card`);
  card.classList.add('win-flash');
  setTimeout(()=>card.classList.remove('win-flash'),1200);
}

// ── Timer ──────────────────────────────────────────────────────────────────
function startTimer() {
  stopTimer();
  G.timerInterval=setInterval(()=>{
    const cp=G.currentPlayer;
    G.timeLeft[cp]=Math.max(0,G.timeLeft[cp]-1);
    updateTimerDisplay();
    if(G.timeLeft[cp]<=0){stopTimer();endRound(1-cp);}
  },1000);
}
function stopTimer() {
  if(G.timerInterval){clearInterval(G.timerInterval);G.timerInterval=null;}
}
function updateTimerDisplay() {
  for(let i=0;i<2;i++){
    const t=G.timeLeft[i];
    const el=document.getElementById(`p${i+1}-timer`);
    el.textContent=`${Math.floor(t/60)}:${(t%60).toString().padStart(2,'0')}`;
    el.classList.toggle('warning',t<=10&&G.currentPlayer===i&&!G.gameOver);
  }
}

// ── UI helpers ─────────────────────────────────────────────────────────────
function setStatus(msg) { document.getElementById('status-bar').textContent=msg; }

function updateTurnBanner() {
  const cp=G.currentPlayer;
  const name=cp===0?'Player 1':(G.mode==='ai'?'AI':'Player 2');
  const color=G.colors[cp];
  const banner=document.getElementById('turn-banner');
  banner.textContent=`${name}'s Turn`;
  banner.style.color=color;
  banner.style.textShadow=`0 0 12px ${color}88`;
  banner.classList.remove('turn-pop');
  void banner.offsetWidth; // reflow to restart animation
  banner.classList.add('turn-pop');
}

function updateActivePlayer() {
  document.getElementById('p1-card').classList.toggle('active-player',G.currentPlayer===0);
  document.getElementById('p2-card').classList.toggle('active-player',G.currentPlayer===1);
  // pulse the active avatar
  ['p1-dot','p2-dot'].forEach((id,i)=>{
    document.getElementById(id).classList.toggle('avatar-active',G.currentPlayer===i);
  });
  updateTimerDisplay();
}

function updateScoreDots() {
  for(let i=0;i<2;i++){
    const c=document.getElementById(`p${i+1}-dots`); c.innerHTML='';
    for(let d=0;d<G.totalRounds;d++){
      const dot=document.createElement('div');
      dot.className='score-dot'+(d<G.scores[i]?' filled':'');
      dot.style.setProperty('--dot-color',G.colors[i]);
      c.appendChild(dot);
    }
  }
}

// ── Result ─────────────────────────────────────────────────────────────────
function showResult() {
  const [s0,s1]=G.scores;
  const p2=G.mode==='ai'?'AI':'Player 2';
  let title,trophy;
  if(s0>s1){title='Player 1 Wins!';trophy='🏆';}
  else if(s1>s0){title=`${p2} Wins!`;trophy='🏆';}
  else{title="It's a Draw!";trophy='🤝';}
  document.getElementById('result-trophy').textContent=trophy;
  document.getElementById('result-title').textContent=title;
  document.getElementById('result-score-row').innerHTML=`
    <div class="result-score-card ${s0>=s1&&s0>0?'winner':''}">
      <div class="rs-name" style="color:${G.colors[0]}">Player 1</div>
      <div class="rs-num">${s0}</div>
    </div>
    <div class="result-score-card ${s1>=s0&&s1>0?'winner':''}">
      <div class="rs-name" style="color:${G.colors[1]}">${p2}</div>
      <div class="rs-num">${s1}</div>
    </div>`;
  document.getElementById('result-log').innerHTML=G.roundLog.map(e=>`
    <div class="log-entry">
      Round ${e.round} (${boardSizeForRound(e.round)}×${boardSizeForRound(e.round)}) —
      <span class="log-winner">${e.winner} wins</span>
      ${e.streak>=2?`<span class="log-streak">🔥${e.streak}</span>`:''}
    </div>`).join('');
  showScreen('screen-result');
}

function playAgain() { buildSetupScreen(); showScreen('screen-setup'); }
