# Smart ID — Local Dev

Quick steps to run frontend and backend locally.

1) Frontend

```bash
cd "c:/Users/vamsh/OneDrive/Desktop/samrt id card/frontend"
npm install
# Start Vite dev server
npm run dev
```

2) Backend

```bash
cd "c:/Users/vamsh/OneDrive/Desktop/samrt id card/backend"
npm install
# Start backend (uses ts-node-dev)
npm run dev
```

Notes:
- The frontend expects the backend at `http://localhost:3001` for `/api/*` endpoints.
- Install Tailwind by running `npm install` in the frontend; Vite + PostCSS + Tailwind config files are included.
