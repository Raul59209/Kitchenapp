<script setup>
import { ref, onMounted } from 'vue'

const ingredients = ref([])

// 1. Load data from Java Backend
async function fetchPantry() {
  const data = await $fetch('http://localhost:8080/api/pantry')
  ingredients.value = data
}

// 2. Add data to Java Backend
async function addIngredient() {
  await $fetch('http://localhost:8080/api/pantry', {
    method: 'POST',
    body: { name: 'Flour', amount: 500, unit: 'g' }
  })
  fetchPantry() // Refresh list
}

onMounted(() => fetchPantry())
</script>

<template>
  <div class="p-10 bg-gray-100 min-h-screen">
    <h1 class="text-3xl font-bold mb-5">My Pantry</h1>
    
    <ul class="bg-white rounded shadow p-4 mb-4">
      <li v-for="item in ingredients" :key="item.name" class="border-b py-2">
        {{ item.name }} - {{ item.amount }} {{ item.unit }}
      </li>
    </ul>

    <button @click="addIngredient" class="bg-blue-500 text-white px-4 py-2 rounded">
      Add Flour (Test)
    </button>
  </div>
</template>