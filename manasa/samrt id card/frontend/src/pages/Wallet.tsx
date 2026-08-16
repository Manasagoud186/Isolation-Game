import React, { useEffect, useState } from "react";

export default function Wallet() {
  const [balance, setBalance] = useState<number>(0);
  const [amount, setAmount] = useState<string>("");

  useEffect(() => {
    fetch("http://localhost:3001/api/wallet")
      .then((r) => r.json())
      .then((d) => setBalance(d.balance || 0))
      .catch(() => {});
  }, []);

  function addMoney() {
    const value = Number(amount);
    if (!value) return;
    fetch("http://localhost:3001/api/wallet", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ amount: value }),
    })
      .then((r) => r.json())
      .then((d) => setBalance(d.balance))
      .catch(() => {});
  }

  return (
    <div className="p-8">
      <h2 className="text-2xl font-bold">Wallet</h2>
      <div className="mt-6 grid grid-cols-2 gap-6">
        <div className="bg-white p-6 rounded shadow">
          <h3 className="font-semibold">Balance</h3>
          <p className="text-2xl font-bold mt-2">₹{balance.toFixed(2)}</p>
        </div>
        <div className="bg-white p-6 rounded shadow">
          <h3 className="font-semibold">Add Money</h3>
          <div className="mt-2">
            <input value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="Amount" className="p-2 border rounded" />
            <button onClick={addMoney} className="ml-2 bg-blue-600 text-white px-4 py-2 rounded">Add</button>
          </div>
        </div>
      </div>
    </div>
  );
}
