"use client";

import Link from "next/link";
import { ReactNode } from "react";

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body className="min-h-screen bg-gray-100">
        <header className="bg-white border-b shadow-sm">
          <nav className="max-w-6xl mx-auto p-4 flex items-center gap-6">
            <h1 className="text-2xl font-semibold">Yummy Dungeon</h1>

            <Link href="/pantry" className="text-gray-600 hover:text-black">
              Pantry
            </Link>

            <Link href="/recipes" className="text-gray-600 hover:text-black">
              Recipes
            </Link>
          </nav>
        </header>

        <main className="max-w-6xl mx-auto p-6">
          {children}
        </main>
      </body>
    </html>
  );
}