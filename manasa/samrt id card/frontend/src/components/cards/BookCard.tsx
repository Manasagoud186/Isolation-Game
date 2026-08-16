import React from "react";

export default function BookCard({ title, author }: any) {
  return (
    <div className="bg-white p-4 rounded shadow">
      <h5 className="font-semibold">{title}</h5>
      <p className="text-gray-500 text-sm">{author}</p>
    </div>
  );
}
