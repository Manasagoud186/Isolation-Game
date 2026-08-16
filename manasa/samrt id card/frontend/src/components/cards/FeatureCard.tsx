import React from "react";

export default function FeatureCard({ title, children }: any) {
  return (
    <div className="bg-white p-6 rounded shadow">
      <h4 className="font-semibold mb-2">{title}</h4>
      <div className="text-sm text-gray-600">{children}</div>
    </div>
  );
}
