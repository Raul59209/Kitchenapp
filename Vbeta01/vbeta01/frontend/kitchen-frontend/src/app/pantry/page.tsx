"use client";

import { useEffect, useState } from "react";
import { Search, Filter, Package } from "lucide-react";

import { getPantries, addPantryItem, updatePantryItem, deletePantryItem } from "@/src/lib/api";
import type { PantryItem } from "@/src/lib/types";

import { PantryItemCard } from "@/src/components/PantryItemCard";
import { AddItemDialog } from "@/src/components/AddItemDialog";
import { Input } from "@/src/components/ui/input";
import {
  Select,
  SelectTrigger,
  SelectContent,
  SelectItem,
  SelectValue,
} from "@/src/components/ui/select";

export default function PantryPage() {
  const [items, setItems] = useState<PantryItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("all");

  const userId = 1;

  useEffect(() => {
    loadPantry();
  }, []);

  async function loadPantry() {
    setLoading(true);
    try {
      const data = await getPantries(userId);
      setItems(data);
    } finally {
      setLoading(false);
    }
  }

  async function handleAddItem(ingredientId: number, amount: number, unit: string, category: string, expirationDate?: string, notes?: string) {
    const newItem = await addPantryItem(userId, { ingredientId, amount, unit, category, expirationDate, notes });
    setItems(prev => [newItem, ...prev]);
  }

  async function handleEditItem(id: number, amount: number) {
    const updated = await updatePantryItem(userId, id, { amount });
    setItems(prev => prev.map(i => (i.id === id ? updated : i)));
  }

  async function handleDeleteItem(id: number) {
    await deletePantryItem(userId, id);
    setItems(prev => prev.filter(i => i.id !== id));
  }

  const categories = ["all", ...new Set(items.map(i => i.ingredient.group))];

  const filtered = items.filter(i => {
    const matchesSearch = i.ingredient.name.toLowerCase().includes(search.toLowerCase());
    const matchesCategory = category === "all" || i.ingredient.group === category;
    return matchesSearch && matchesCategory;
  });

  return (
    <div className="max-w-7xl mx-auto p-6">
      {/* Header */}
      <div className="mb-8">
        <div className="flex items-center gap-3 mb-2">
          <Package className="w-8 h-8 text-primary" />
          <h1 className="text-4xl font-bold">Yummy Dungeon</h1>
        </div>
        <p className="text-muted-foreground">
          Keep track of your pantry items and recipes
        </p>
      </div>

      {/* Tabs */}
      <div className="flex gap-6 border-b mb-6 pb-2">
        <span className="font-medium text-primary border-b-2 border-primary pb-1">
          Pantry Items
        </span>
        <a href="/recipes" className="text-muted-foreground hover:text-foreground">
          Recipes
        </a>
      </div>

      {/* Controls */}
      <div className="flex flex-col sm:flex-row gap-4 mb-6">
        <div className="relative flex-1">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-muted-foreground" />
          <Input
            placeholder="Search items..."
            value={search}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSearch(e.target.value)}
            className="pl-10"
          />
        </div>

        <div className="flex gap-3">
          <div className="flex items-center gap-2 min-w-[180px]">
            <Filter className="w-4 h-4 text-muted-foreground" />
            <Select value={category} onValueChange={setCategory}>
              <SelectTrigger>
                <SelectValue placeholder="All Categories" />
              </SelectTrigger>
              <SelectContent>
                {categories.map(cat => (
                  <SelectItem key={cat} value={cat}>
                    {cat === "all" ? "All Categories" : cat}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <AddItemDialog onAddItem={handleAddItem} />
        </div>
      </div>

      {/* Items Grid */}
      {loading ? (
        <div className="text-center py-12">
          <Package className="w-16 h-16 mx-auto text-muted-foreground mb-4" />
          <h3 className="text-xl mb-2">Loading items...</h3>
        </div>
      ) : filtered.length === 0 ? (
        <div className="text-center py-12">
          <Package className="w-16 h-16 mx-auto text-muted-foreground mb-4" />
          <h3 className="text-xl mb-2">No items found</h3>
          <p className="text-muted-foreground">
            {search || category !== "all"
              ? "Try adjusting your search or filters"
              : "Add your first pantry item to get started"}
          </p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {filtered.map(item => (
            <PantryItemCard
              key={item.id}
              item={item}
              onEdit={(amount) => handleEditItem(item.id, amount)}
              onDelete={() => handleDeleteItem(item.id)}
            />
          ))}
        </div>
      )}

      {/* Stats */}
      <div className="mt-8 p-4 rounded-lg bg-muted/50">
        <p className="text-sm text-muted-foreground">
          Total items:{" "}
          <span className="font-medium text-foreground">{items.length}</span>
          {category !== "all" && (
            <>
              {" "}• Showing:{" "}
              <span className="font-medium text-foreground">{filtered.length}</span>
            </>
          )}
        </p>
      </div>
    </div>
  );
}