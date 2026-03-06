"use client";

import { useEffect, useState } from "react";

interface Recipe {
  id: number;
  name: string;
}

export default function RecipesPage() {
  const [recipes, setRecipes] = useState<Recipe[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/recipes")
      .then(res => res.json())
      .then(setRecipes);
  }, []);

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-semibold">Recipes</h2>
        <button className="px-4 py-2 bg-green-600 text-white rounded">
          Add Recipe
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {recipes.map(recipe => (
          <div key={recipe.id} className="p-4 bg-white rounded shadow">
            {recipe.name}
          </div>
        ))}
      </div>
    </div>
  );
}