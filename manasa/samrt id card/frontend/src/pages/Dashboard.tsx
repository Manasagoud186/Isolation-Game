import React from "react";
import StatCard from "../components/cards/StatCard";

export default function Dashboard() {
  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold">Welcome back, Sreesha!</h1>

      <div className="grid grid-cols-4 gap-4 mt-6">
        <StatCard title="Wallet Balance" value="₹0.00" />
        <StatCard title="Attendance" value="2" />
        <StatCard title="Books Issued" value="0" />
        <StatCard title="Pending Leaves" value="0" />
      </div>

      <div className="grid grid-cols-2 gap-6 mt-8">
        <div className="bg-white p-6 rounded shadow">
          <h3 className="font-semibold">Recent Attendance</h3>
          <p className="text-sm mt-2">Check Out – Main Campus</p>
        </div>

        <div className="bg-white p-6 rounded shadow text-center">
          <h3 className="font-semibold">Borrowed Books</h3>
          <p className="text-gray-400 mt-4">No books borrowed</p>
        </div>
      </div>
    </div>
  );
}
