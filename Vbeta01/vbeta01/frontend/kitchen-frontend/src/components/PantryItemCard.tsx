import { useState } from "react";
import { Trash2, Pencil } from "lucide-react";
import type { PantryItem } from "@/src/lib/types";

import { Button } from "@/src/components/ui/button";
import {
  Card,
  CardHeader,
  CardTitle,
  CardDescription,
  CardContent,
} from "@/src/components/ui/card";
import { Badge } from "@/src/components/ui/badge";
import { EditItemDialog } from "@/src/components/EditItemDialog";

import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/src/components/ui/alert-dialog";

interface Props {
  item: PantryItem;
  onEdit: (amount: number) => void;
  onDelete: () => void;
}

export function PantryItemCard({ item, onEdit, onDelete }: Props) {
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);

  return (
    <>
      <Card>
        <CardHeader>
          <div className="flex justify-between items-start">
            <div>
              <CardTitle>{item.ingredient.name}</CardTitle>
              <CardDescription>
                {item.amount} {item.unit}
              </CardDescription>
            </div>

            <div className="flex gap-1">
              <EditItemDialog
                item={item}
                onEdit={(updates) => onEdit(updates.amount!)}
              />

              <Button
                onClick={() => setShowDeleteDialog(true)}
                className="p-2 h-auto"
              >
                <Trash2 className="size-4 text-destructive" />
              </Button>
            </div>
          </div>
        </CardHeader>

        <CardContent>
          <Badge>{item.ingredient.group}</Badge>
        </CardContent>
      </Card>

      <AlertDialog open={showDeleteDialog} onOpenChange={setShowDeleteDialog}>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>Delete Item</AlertDialogTitle>
            <AlertDialogDescription>
              Remove {item.ingredient.name} from your pantry?
            </AlertDialogDescription>
          </AlertDialogHeader>

          <AlertDialogFooter>
            <AlertDialogCancel>Cancel</AlertDialogCancel>
            <AlertDialogAction onClick={onDelete}>
              Delete
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
}