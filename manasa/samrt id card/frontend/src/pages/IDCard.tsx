import React from "react";
import SmartIDCard from "../components/SmartIDCard";

export default function IDCard() {
  return (
    <div className="p-8">
      <h2 className="text-2xl font-bold">My ID Card</h2>
      <div className="mt-6">
        <SmartIDCard />
      </div>
    </div>
  );
}
