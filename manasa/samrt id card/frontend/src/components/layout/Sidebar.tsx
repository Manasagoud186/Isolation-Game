import React from "react";

const menu = [
  "Dashboard",
  "My ID Card",
  "Attendance",
  "Library",
  "Leave Requests",
  "Transport",
  "Wallet"
];

export default function Sidebar() {
  return (
    <aside className="w-64 bg-[#0B1C33] text-white min-h-screen p-4">
      <h2 className="text-xl font-bold mb-8">Smart ID</h2>
      {menu.map((item) => (
        <div
          key={item}
          className="px-4 py-2 rounded hover:bg-blue-600 cursor-pointer"
        >
          {item}
        </div>
      ))}
      <div style={{ position: "absolute", bottom: 24 }} className="text-sm text-gray-400">
        Sreesha Thummalpalli
        <br />Student
      </div>
    </aside>
  );
}
