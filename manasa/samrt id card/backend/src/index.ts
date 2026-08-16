
import express, { Request, Response } from "express";
import cors from "cors";

const app = express();
app.use(cors());
app.use(express.json());

let walletBalance = 0;

app.post("/api/attendance", (req: Request, res: Response) => {
  const { type } = req.body as { type?: string };
  // In a real app you'd persist attendance; here we just acknowledge
  res.json({ success: true, type });
});

app.get("/api/profile", (_req: Request, res: Response) => {
  res.json({
    id: "CARD-524C31C9",
    name: "Sreesha Thummalpalli",
    role: "Student"
  });
});

const books = [
  { id: "b1", title: "Clean Code", author: "Robert C. Martin" },
  { id: "b2", title: "You Don't Know JS", author: "Kyle Simpson" }
];

app.get("/api/library", (_req: Request, res: Response) => {
  res.json({ books });
});

app.get("/api/wallet", (_req: Request, res: Response) => {
  res.json({ balance: walletBalance });
});

app.post("/api/wallet", (req: Request, res: Response) => {
  const { amount } = req.body as { amount?: number };
  const num = Number(amount) || 0;
  walletBalance += num;
  res.json({ balance: walletBalance });
});

app.listen(3001, () => console.log("Backend running on http://localhost:3001"));
