import * as React from "react";
import {
  Select as SelectPrimitive,
  SelectContent as SelectPrimitiveContent,
  SelectItem as SelectPrimitiveItem,
  SelectTrigger as SelectPrimitiveTrigger,
  SelectValue as SelectPrimitiveValue,
} from "@radix-ui/react-select";

import { cn } from "@/src/lib/utils";

export const Select = SelectPrimitive;

export const SelectTrigger = React.forwardRef<
  HTMLButtonElement,
  React.ComponentPropsWithoutRef<typeof SelectPrimitiveTrigger>
>(({ className, ...props }, ref) => (
  <SelectPrimitiveTrigger
    ref={ref}
    className={cn(
      "flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm",
      "focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2",
      className
    )}
    {...props}
  />
));

SelectTrigger.displayName = SelectPrimitiveTrigger.displayName;

export const SelectValue = SelectPrimitiveValue;

export const SelectContent = React.forwardRef<
  HTMLDivElement,
  React.ComponentPropsWithoutRef<typeof SelectPrimitiveContent>
>(({ className, ...props }, ref) => (
  <SelectPrimitiveContent
    ref={ref}
    className={cn(
      "z-50 min-w-[8rem] overflow-hidden rounded-md border bg-popover text-popover-foreground shadow-md",
      className
    )}
    {...props}
  />
));

SelectContent.displayName = SelectPrimitiveContent.displayName;

export const SelectItem = React.forwardRef<
  HTMLDivElement,
  React.ComponentPropsWithoutRef<typeof SelectPrimitiveItem>
>(({ className, ...props }, ref) => (
  <SelectPrimitiveItem
    ref={ref}
    className={cn(
      "relative flex w-full cursor-pointer select-none items-center rounded-sm py-1.5 px-2 text-sm outline-none",
      "focus:bg-accent focus:text-accent-foreground",
      className
    )}
    {...props}
  />
));

SelectItem.displayName = SelectPrimitiveItem.displayName;