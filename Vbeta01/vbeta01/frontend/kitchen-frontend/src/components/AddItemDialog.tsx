"use client";

import { useState } from "react";
import { Plus } from "lucide-react";

import { Button } from "@/src/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogFooter,
  DialogTrigger,
} from "@/src/components/ui/dialog";

import { Input } from "@/src/components/ui/input";
import {
  Select,
  SelectTrigger,
  SelectContent,
  SelectItem,
  SelectValue,
} from "@/src/components/ui/select";

import type { Ingredient } from "@/src/lib/types";
import { getIngredients } from "@/src/lib/api";

interface Props {
  onAddItem: (ingredientId: number, amount: number, unit: string, category: string, expirationDate?: string, notes?: string) => void;
}

export function AddItemDialog({ onAddItem }: Props) {
  const [open, setOpen] = useState(false);

  const [ingredients, setIngredients] = useState<Ingredient[]>([]);
  const [ingredientId, setIngredientId] = useState<number | null>(null);
  const [amount, setAmount] = useState(1);
  const [unit, setUnit] = useState("pcs");
  const [category, setCategory] = useState("");
  const [expirationDate, setExpirationDate] = useState("");
  const [notes, setNotes] = useState("");

  async function loadIngredients() {
    const data = await getIngredients();
    setIngredients(data);
  }

  function handleSubmit() {
    if (!ingredientId) return;

    onAddItem(ingredientId, amount, unit, category, expirationDate, notes);
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={(v) => {
      setOpen(v);
      if (v) loadIngredients();
    }}>
      <DialogTrigger asChild>
        <Button className="flex items-center gap-2">
          <Plus className="w-4 h-4" />
          Add Item
        </Button>
      </DialogTrigger>

      <DialogContent>
        <DialogHeader>
          <DialogTitle>Add Pantry Item</DialogTitle>
          <DialogDescription>
            Add a new item to your pantry.
          </DialogDescription>
        </DialogHeader>

        <div className="space-y-4">
          {/* Ingredient */}
          <div>
            <label className="text-sm font-medium">Item Name *</label>
            <Select onValueChange={(v: any) => setIngredientId(Number(v))}>
              <SelectTrigger>
                <SelectValue placeholder="Select ingredient" />
              </SelectTrigger>
              <SelectContent>
                {ingredients.map((ing) => (
                  <SelectItem key={ing.id} value={String(ing.id)}>
                    {ing.name}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          {/* Quantity */}
          <div>
            <label className="text-sm font-medium">Quantity *</label>
            <Input
              type="number"
              min={1}
              value={amount}
              onChange={(e) => setAmount(Number(e.target.value))}
            />
          </div>

          {/* Unit */}
          <div>
            <label className="text-sm font-medium">Unit</label>
            <Input
              value={unit}
              onChange={(e) => setUnit(e.target.value)}
            />
          </div>
        </div>

        <DialogFooter>
          <Button onClick={() => setOpen(false)}>
            Cancel
          </Button>
          <Button onClick={handleSubmit}>Add Item</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}