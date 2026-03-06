export type Ingredient = {
    id: number;
    name: string;
    group: string;
}

export type Recipe = {
    id: number;
    name: string;
    description: string;
    instructions: string;
    items: RecipeItem[];
}

export type RecipeItem = {
    id: number;
    ingredient: Ingredient;
    amount: number;
    unit: string;
};

export type User = {
    id: number;
    username: string;
    password: string;
    email: string;
    pantryItems: PantryItem[];
}

export type PantryItem = {
    id: number;
    ingredient: Ingredient;
    amount: number;
    unit: string;
    expirationDate: string | null;
    notes: string | null;
    category: string;
}