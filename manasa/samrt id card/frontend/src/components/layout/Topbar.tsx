import React from "react";

export default function Topbar() {
  return (
    <header className="flex justify-between items-center px-10 py-4 bg-white shadow">
      <h1 className="font-bold text-lg">Smart ID</h1>
      <div className="space-x-4">
        <button>Login</button>
        <button className="bg-blue-600 text-white px-4 py-2 rounded">Get Started</button>
      </div>
    </header>
  );
}
