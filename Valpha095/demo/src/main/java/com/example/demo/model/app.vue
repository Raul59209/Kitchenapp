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
<script setup>
import { ref, onMounted } from 'vue'

// --- STATE ---
const activeTab = ref('pantry') // 'pantry' or 'recipes'
const pantryItems = ref([])
const recipes = ref([])

// Form Inputs
const newItem = ref({ name: '', amount: '', unit: '' })

// --- API ACTIONS ---
const API_URL = 'http://localhost:8080/api'

async function fetchData() {
  // Fetch Pantry
  const pData = await $fetch(`${API_URL}/pantry`)
  pantryItems.value = pData
  
  // Fetch Recipes (Mocking data for design demo if API is empty)
  // const rData = await $fetch(`${API_URL}/recipes`) 
  // recipes.value = rData
}

async function addItem() {
  if (!newItem.value.name) return
  
  await $fetch(`${API_URL}/pantry`, {
    method: 'POST',
    body: newItem.value
  })
  
  // Clear form and refresh
  newItem.value = { name: '', amount: '', unit: '' }
  fetchData()
}

// Load data on startup
onMounted(() => {
  fetchData()
})
</script>

<template>
  <div class="min-h-screen bg-slate-50 text-slate-800 font-sans p-8">
    
    <header class="max-w-4xl mx-auto mb-10 flex justify-between items-center">
      <div>
        <h1 class="text-4xl font-extrabold text-emerald-600 tracking-tight">Yummy Dungeon</h1>
        <p class="text-slate-500 mt-1">Manage your ingredients and conquer recipes.</p>
      </div>
      <div class="bg-white p-1 rounded-lg shadow-sm border border-slate-200 flex">
        <button 
          @click="activeTab = 'pantry'"
          :class="activeTab === 'pantry' ? 'bg-emerald-100 text-emerald-700 font-semibold' : 'text-slate-500 hover:text-slate-700'"
          class="px-4 py-2 rounded-md transition-all duration-200">
          Pantry
        </button>
        <button 
          @click="activeTab = 'recipes'"
          :class="activeTab === 'recipes' ? 'bg-orange-100 text-orange-700 font-semibold' : 'text-slate-500 hover:text-slate-700'"
          class="px-4 py-2 rounded-md transition-all duration-200">
          Cookbook
        </button>
      </div>
    </header>

    <main class="max-w-4xl mx-auto">
      
      <div v-if="activeTab === 'pantry'" class="animate-fade-in">
        
        <div class="bg-white p-6 rounded-2xl shadow-lg border border-slate-100 mb-8">
          <h2 class="text-lg font-bold mb-4 text-slate-700">Stock the Dungeon</h2>
          <div class="flex gap-4">
            <input v-model="newItem.name" type="text" placeholder="Ingredient Name (e.g. Garlic)" class="flex-1 p-3 border border-slate-200 rounded-xl focus:ring-2 focus:ring-emerald-500 outline-none" />
            <input v-model="newItem.amount" type="number" placeholder="Qty" class="w-24 p-3 border border-slate-200 rounded-xl focus:ring-2 focus:ring-emerald-500 outline-none" />
            <input v-model="newItem.unit" type="text" placeholder="Unit" class="w-24 p-3 border border-slate-200 rounded-xl focus:ring-2 focus:ring-emerald-500 outline-none" />
            <button @click="addItem" class="bg-emerald-600 hover:bg-emerald-700 text-white font-bold px-6 py-3 rounded-xl transition-colors shadow-md shadow-emerald-200">
              Add
            </button>
          </div>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <div v-for="(item, index) in pantryItems" :key="index" class="bg-white p-5 rounded-xl shadow-sm border border-slate-100 hover:shadow-md transition-shadow relative group">
            <div class="absolute top-4 right-4 text-slate-300 group-hover:text-red-400 cursor-pointer">
              <span class="text-xs font-bold">REMOVE</span>
            </div>
            <h3 class="font-bold text-lg text-slate-800 capitalize">{{ item.name }}</h3>
            <p class="text-emerald-600 font-medium mt-1">{{ item.amount }} <span class="text-sm text-slate-400">{{ item.unit }}</span></p>
          </div>
        </div>
        
        <div v-if="pantryItems.length === 0" class="text-center py-10 text-slate-400">
          Your pantry is empty. Start stocking!
        </div>
      </div>

      <div v-else class="animate-fade-in">
        <div class="grid grid-cols-1 gap-4">
           <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 flex justify-between items-center">
              <div>
                <h3 class="font-bold text-xl text-slate-800">Mystery Stew</h3>
                <p class="text-slate-500 text-sm mt-1">Requires: Potato, Garlic, Milk</p>
              </div>
              <div class="flex items-center gap-3">
                 <span class="px-3 py-1 bg-green-100 text-green-700 text-xs font-bold rounded-full uppercase tracking-wide">Perfect Match</span>
                 <button class="bg-slate-100 hover:bg-slate-200 text-slate-600 px-4 py-2 rounded-lg font-medium text-sm transition-colors">View</button>
              </div>
           </div>
        </div>
      </div>

    </main>
  </div>
</template>

<style>
/* Simple fade animation */
.animate-fade-in {
  animation: fadeIn 0.3s ease-in-out;
}
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>