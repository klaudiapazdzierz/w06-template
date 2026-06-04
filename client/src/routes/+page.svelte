<script lang="ts">
    import "../app.css";

    import FoodCard from './FoodCard.svelte';
    import type { Meal } from '$lib/types';
    import type { PageProps } from "./$types";
    import { setCookie } from '$lib';
    import { invalidateAll } from '$app/navigation';

    let { data }: PageProps = $props();

    let meals: Meal[] = $state([]);
    // Seed (and re-seed after invalidateAll) from the load result.
    $effect.pre(() => {
        meals = data.meals;
    });

    let usernameInput = $state('');
    let submitting = $state(false);
    let usernameInputEl: HTMLInputElement | undefined = $state();

    $effect(() => {
        if (data.needsUsername) usernameInputEl?.focus();
    });

    const submitUsername = async (event: SubmitEvent) => {
        event.preventDefault();
        const trimmed = usernameInput.trim();
        if (!trimmed) return;
        submitting = true;
        setCookie('username', trimmed, 30);
        await invalidateAll();
        submitting = false;
    };
</script>

{#if data.needsUsername}
    <div class="username-overlay" role="dialog" aria-modal="true" aria-labelledby="username-title">
        <form class="username-card" onsubmit={submitUsername}>
            <h2 id="username-title">Welcome 👋</h2>
            <p>Pick a username so we can remember your favorite meals.</p>
            <label for="username-input" class="visually-hidden">Username</label>
            <input
                id="username-input"
                type="text"
                bind:value={usernameInput}
                bind:this={usernameInputEl}
                placeholder="e.g. ani"
                autocomplete="off"
                required
            />
            <button type="submit" disabled={submitting || !usernameInput.trim()}>
                {submitting ? 'Saving…' : 'Continue'}
            </button>
        </form>
    </div>
{:else}
    <main>
        <header>
            <h1>Garching Campus Canteen</h1>
            <p>Today's menu offerings</p>
        </header>

        <!-- Recommendation Banner -->
        {#await data.recommendation}
            <div class="recommendation-banner loading">
                <div class="recommendation-content">
                    <h3>🤖 AI Recommendation</h3>
                    <p>Thinking about today's menu…</p>
                </div>
            </div>
        {:then recommendation}
            {#if recommendation?.recommendation}
                <div class="recommendation-banner">
                    <div class="recommendation-content">
                        <h3>🤖 AI Recommendation</h3>
                        <p>{recommendation.recommendation}</p>
                    </div>
                </div>
            {:else}
                <div class="recommendation-banner empty">
                    <div class="recommendation-content">
                        <h3>🤖 AI Recommendation</h3>
                        <p>No recommendations available. Try adding some favorite meals first!</p>
                    </div>
                </div>
            {/if}
        {:catch}
            <div class="recommendation-banner empty">
                <div class="recommendation-content">
                    <h3>🤖 AI Recommendation</h3>
                    <p>Couldn't reach the recommendation service.</p>
                </div>
            </div>
        {/await}

        {#if meals.length === 0}
            <div class="no-results">
                <p>Loading menu items…</p>
            </div>
        {:else}
            <div class="food-grid">
                {#each meals as {}, i}
                    <FoodCard bind:meal={meals[i]} />
                {/each}
            </div>
        {/if}
    </main>
{/if}
