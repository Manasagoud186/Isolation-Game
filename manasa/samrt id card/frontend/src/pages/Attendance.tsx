import React, { useState } from "react";

export default function Attendance() {
  const [status, setStatus] = useState<string | null>(null);

  function postAttendance(type: "in" | "out") {
    fetch("http://localhost:3001/api/attendance", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ type }),
    })
      .then((r) => r.json())
      .then(() => setStatus(type === "in" ? "Checked in" : "Checked out"))
      .catch(() => setStatus("Error"));
  }

  return (
    <div className="p-8">
      <div className="bg-gradient-to-r from-blue-600 to-purple-600 p-6 rounded text-white">
        <h2 className="font-bold">Quick Attendance</h2>

        <select className="mt-4 p-2 rounded text-black">
          <option>Main Campus</option>
        </select>

        <div className="flex gap-4 mt-4">
          <button onClick={() => postAttendance("in")} className="bg-green-500 px-6 py-2 rounded">Check In</button>
          <button onClick={() => postAttendance("out")} className="bg-purple-500 px-6 py-2 rounded">Check Out</button>
        </div>

        {status && <p className="mt-4">{status}</p>}
      </div>
    </div>
  );
}
