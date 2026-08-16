import React from "react";

export default function Landing() {
  return (
    <div className="min-h-screen bg-gray-50">
      <header className="flex justify-between items-center px-10 py-4 bg-white shadow">
        <h1 className="font-bold text-lg">Smart ID</h1>
        <div className="space-x-4">
          <button>Login</button>
          <button className="bg-blue-600 text-white px-4 py-2 rounded">Get Started</button>
        </div>
      </header>

      <section className="text-center py-24">
        <span className="text-blue-600 font-medium">College Smart ID Card System</span>
        <h2 className="text-5xl font-bold mt-4">
          One Card, <span className="text-blue-600">Endless Possibilities</span>
        </h2>
        <p className="text-gray-500 mt-6 max-w-xl mx-auto">
          Manage attendance, library, transport and payments with one digital card
        </p>
        <div className="mt-8 space-x-4">
          <button className="bg-blue-600 text-white px-6 py-3 rounded">Get Started →</button>
          <button className="border px-6 py-3 rounded">Learn More</button>
        </div>
      </section>
    </div>
  );
}
