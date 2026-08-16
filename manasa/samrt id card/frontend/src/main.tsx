import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import Landing from "./pages/Landing";
import Dashboard from "./pages/Dashboard";
import IDCard from "./pages/IDCard";
import Attendance from "./pages/Attendance";
import Library from "./pages/Library";
import Wallet from "./pages/Wallet";
import Leave from "./pages/Leave";
import "./index.css";

createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/app" element={<App />}>
          <Route index element={<Dashboard />} />
          <Route path="id" element={<IDCard />} />
          <Route path="attendance" element={<Attendance />} />
          <Route path="library" element={<Library />} />
          <Route path="wallet" element={<Wallet />} />
          <Route path="leave" element={<Leave />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
