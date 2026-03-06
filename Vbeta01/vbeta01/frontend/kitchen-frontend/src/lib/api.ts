import type { Ingredient, Recipe, PantryItem, User } from "@/src/lib/types";

const API_BASE = "http://localhost:8080";

async function getIngredients(): Promise<Ingredient[]> {
  const res = await fetch(`${API_BASE}/ingredients`, {
    cache: "no-store",
  });
  return res.json();
}

async function getRecipes(): Promise<Recipe[]> {
  const res = await fetch(`${API_BASE}/recipes`, {
    cache: "no-store",
  });
  return res.json();
}

async function getPantries(userId: number): Promise<PantryItem[]> {
  const res = await fetch(`${API_BASE}/users/${userId}/pantry_items`, {
    cache: "no-store",
  });
  return res.json();
}

async function getUsers(): Promise<User[]> {
  const res = await fetch(`${API_BASE}/users`, {
    cache: "no-store",
  });
  return res.json();
}

export async function addPantryItem(
  userId: number,
  data: { ingredientId: number; amount: number; unit: string; category: string; expirationDate?: string; notes?: string }
): Promise<PantryItem> {
  const res = await fetch(`${API_BASE}/users/${userId}/pantry_items`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function updatePantryItem(
  userId: number,
  itemId: number,
  updates: { amount?: number; unit?: string; category?: string; expirationDate?: string; notes?: string }
): Promise<PantryItem> {
  const res = await fetch(`${API_BASE}/users/${userId}/pantry_items/${itemId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updates),
  });
  return res.json();
}

export async function deletePantryItem(
  userId: number,
  itemId: number
): Promise<void> {
  await fetch(`${API_BASE}/users/${userId}/pantry_items/${itemId}`, {
    method: "DELETE",
  });
}

export { getIngredients, getRecipes, getPantries, getUsers };