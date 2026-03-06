import { useState } from 'react';
import { Plus, X } from 'lucide-react';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogDescription,
} from '@/src/components/ui/dialog';
import { Button } from '@/src/components/ui/button';
import { Input } from '@/src/components/ui/input';
import { Label } from '@/src/components/ui/label';
import { Textarea } from '@/src/components/ui/textarea';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/src/components/ui/select';

export interface RecipeIngredient {
  name: string;
  amount: number;
  unit: string;
  group?: string;
}

export interface Recipe {
  id: string;
  name: string;
  description: string;
  ingredients: RecipeIngredient[];
  instructions: string;
}

interface AddRecipeDialogProps {
  onAddRecipe: (recipe: Omit<Recipe, 'id'>) => void;
}

const units = ['pcs', 'oz', 'lb', 'g', 'kg', 'ml', 'L', 'cups', 'tbsp', 'tsp'];

const categories = [
  'Grains & Pasta',
  'Canned Goods',
  'Spices & Condiments',
  'Baking',
  'Snacks',
  'Beverages',
  'Produce',
  'Dairy',
  'Meat & Protein',
  'Frozen',
  'Other',
];

export function AddRecipeDialog({ onAddRecipe }: AddRecipeDialogProps) {
  const [open, setOpen] = useState(false);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [instructions, setInstructions] = useState('');
  const [ingredients, setIngredients] = useState<RecipeIngredient[]>([
    { name: '', amount: 1, unit: 'cups', group: 'Other' },
  ]);

  const handleAddIngredient = () => {
    setIngredients([...ingredients, { name: '', amount: 1, unit: 'cups', group: 'Other' }]);
  };

  const handleRemoveIngredient = (index: number) => {
    setIngredients(ingredients.filter((_, i) => i !== index));
  };

  const handleIngredientChange = (
    index: number,
    field: keyof RecipeIngredient,
    value: string | number
  ) => {
    const updated = [...ingredients];
    updated[index] = { ...updated[index], [field]: value };
    setIngredients(updated);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!name.trim() || !instructions.trim()) return;

    const validIngredients = ingredients.filter((ing) => ing.name.trim());
    if (validIngredients.length === 0) return;

    onAddRecipe({
      name: name.trim(),
      description: description.trim(),
      ingredients: validIngredients.map((ing) => ({
        ...ing,
        name: ing.name.trim(),
        group: ing.group?.trim() || undefined,
      })),
      instructions: instructions.trim(),
    });

    // Reset form
    setName('');
    setDescription('');
    setInstructions('');
    setIngredients([{ name: '', amount: 1, unit: 'cups', group: 'Other' }]);
    setOpen(false);
  };

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button>
          <Plus className="size-4 mr-2" />
          Add Recipe
        </Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[600px] max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>Add Recipe</DialogTitle>
          <DialogDescription>
            Create a new recipe with ingredients and instructions.
          </DialogDescription>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="recipe-name">Recipe Name *</Label>
            <Input
              id="recipe-name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="e.g., Spaghetti Carbonara"
              required
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="recipe-description">Description</Label>
            <Textarea
              id="recipe-description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Brief description of the recipe"
              rows={2}
            />
          </div>

          <div className="space-y-3">
            <div className="flex items-center justify-between">
              <Label>Ingredients *</Label>
              <Button type="button" size="sm" variant="outline" onClick={handleAddIngredient}>
                <Plus className="size-4 mr-1" />
                Add Ingredient
              </Button>
            </div>

            {ingredients.map((ingredient, index) => (
              <div key={index} className="flex gap-2 items-start p-3 border rounded-md">
                <div className="flex-1 space-y-2">
                  <Input
                    placeholder="Ingredient name *"
                    value={ingredient.name}
                    onChange={(e) => handleIngredientChange(index, 'name', e.target.value)}
                    required
                  />
                  <div className="grid grid-cols-3 gap-2">
                    <Input
                      type="number"
                      placeholder="Amount"
                      min="0.01"
                      step="0.01"
                      value={ingredient.amount}
                      onChange={(e) => handleIngredientChange(index, 'amount', Number(e.target.value))}
                      required
                    />
                    <Select
                      value={ingredient.unit}
                      onValueChange={(value) => handleIngredientChange(index, 'unit', value)}
                    >
                      <SelectTrigger>
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        {units.map((u) => (
                          <SelectItem key={u} value={u}>
                            {u}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <Select
                      value={ingredient.group || 'Other'}
                      onValueChange={(value) => handleIngredientChange(index, 'group', value)}
                    >
                      <SelectTrigger>
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        {categories.map((cat) => (
                          <SelectItem key={cat} value={cat}>
                            {cat}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                {ingredients.length > 1 && (
                  <Button
                    type="button"
                    variant="ghost"
                    size="sm"
                    onClick={() => handleRemoveIngredient(index)}
                  >
                    <X className="size-4 text-destructive" />
                  </Button>
                )}
              </div>
            ))}
          </div>

          <div className="space-y-2">
            <Label htmlFor="recipe-instructions">Instructions *</Label>
            <Textarea
              id="recipe-instructions"
              value={instructions}
              onChange={(e) => setInstructions(e.target.value)}
              placeholder="Step-by-step instructions"
              rows={6}
              required
            />
          </div>

          <div className="flex justify-end gap-3">
            <Button type="button" variant="outline" onClick={() => setOpen(false)}>
              Cancel
            </Button>
            <Button type="submit">Add Recipe</Button>
          </div>
        </form>
      </DialogContent>
    </Dialog>
  );
}