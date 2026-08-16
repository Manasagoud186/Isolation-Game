import React, { useEffect, useState } from "react";
import BookCard from "../components/cards/BookCard";

export default function Library() {
  const [books, setBooks] = useState<any[]>([]);

  useEffect(() => {
    fetch("http://localhost:3001/api/library")
      .then((r) => r.json())
      .then((data) => setBooks(data.books || []))
      .catch(() => setBooks([]));
  }, []);

  return (
    <div className="p-8">
      <h2 className="text-2xl font-bold">Library</h2>
      <div className="grid grid-cols-2 gap-6 mt-6">
        {books.length === 0 ? (
          <BookCard title="No Results" author="No books issued" />
        ) : (
          books.map((b: any) => <BookCard key={b.id} title={b.title} author={b.author} />)
        )}
      </div>
    </div>
  );
}
