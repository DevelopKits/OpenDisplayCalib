package fr.hd3d.colortribe.color;

import fr.hd3d.colortribe.color.type.Point2f;


/**
 * This class is intented to provide informations about the
 * Planckian Locus.<br>
 * That is to say : the x,y CIE coordinates of the
 * blackbody at a given temperature T in °K.
 * <br><a href=http://en.wikipedia.org/wiki/Planckian_locus>
 * More information on wikipedia</a>.<br>
 * This is a fast implementation based upon precomputed values.<br>
 * <br>
 * Precomputed values are taken from this
 * <a href=http://www.vendian.org/mncharity/dir3/blackbody/UnstableURLs/bbr_color.html>Blackbody color datafile</a>
 * <br>
 * One can refer to this rather <a href=http://www.vendian.org/mncharity/dir3/blackbody/parameters.html>good page</a>.
 * 
 *
 */
public class PlanckianLocus {
    public static final int MIN_TEMPERATURE = 1000;
    public static final int MAX_TEMPERATURE = 40000;
    // CIE 1931  2 degree Color Matching Functions (CMF) with Judd Vos corrections
    // values of x,y from 1000 K to 40 000 K
    private static final float xyValue2Deg[][] = { { 0.6499f, 0.3474f }, { 0.6361f, 0.3594f }, { 0.6226f, 0.3703f }, { 0.6095f, 0.3801f },
            { 0.5966f, 0.3887f }, { 0.5841f, 0.3962f }, { 0.5720f, 0.4025f }, { 0.5601f, 0.4076f }, { 0.5486f, 0.4118f }, { 0.5375f, 0.4150f },
            { 0.5267f, 0.4173f }, { 0.5162f, 0.4188f }, { 0.5062f, 0.4196f }, { 0.4965f, 0.4198f }, { 0.4872f, 0.4194f }, { 0.4782f, 0.4186f },
            { 0.4696f, 0.4173f }, { 0.4614f, 0.4158f }, { 0.4535f, 0.4139f }, { 0.4460f, 0.4118f }, { 0.4388f, 0.4095f }, { 0.4320f, 0.4070f },
            { 0.4254f, 0.4044f }, { 0.4192f, 0.4018f }, { 0.4132f, 0.3990f }, { 0.4075f, 0.3962f }, { 0.4021f, 0.3934f }, { 0.3969f, 0.3905f },
            { 0.3919f, 0.3877f }, { 0.3872f, 0.3849f }, { 0.3827f, 0.3820f }, { 0.3784f, 0.3793f }, { 0.3743f, 0.3765f }, { 0.3704f, 0.3738f },
            { 0.3666f, 0.3711f }, { 0.3631f, 0.3685f }, { 0.3596f, 0.3659f }, { 0.3563f, 0.3634f }, { 0.3532f, 0.3609f }, { 0.3502f, 0.3585f },
            { 0.3473f, 0.3561f }, { 0.3446f, 0.3538f }, { 0.3419f, 0.3516f }, { 0.3394f, 0.3494f }, { 0.3369f, 0.3472f }, { 0.3346f, 0.3451f },
            { 0.3323f, 0.3431f }, { 0.3302f, 0.3411f }, { 0.3281f, 0.3392f }, { 0.3261f, 0.3373f }, { 0.3242f, 0.3355f }, { 0.3223f, 0.3337f },
            { 0.3205f, 0.3319f }, { 0.3188f, 0.3302f }, { 0.3171f, 0.3286f }, { 0.3155f, 0.3270f }, { 0.3140f, 0.3254f }, { 0.3125f, 0.3238f },
            { 0.3110f, 0.3224f }, { 0.3097f, 0.3209f }, { 0.3083f, 0.3195f }, { 0.3070f, 0.3181f }, { 0.3058f, 0.3168f }, { 0.3045f, 0.3154f },
            { 0.3034f, 0.3142f }, { 0.3022f, 0.3129f }, { 0.3011f, 0.3117f }, { 0.3000f, 0.3105f }, { 0.2990f, 0.3094f }, { 0.2980f, 0.3082f },
            { 0.2970f, 0.3071f }, { 0.2961f, 0.3061f }, { 0.2952f, 0.3050f }, { 0.2943f, 0.3040f }, { 0.2934f, 0.3030f }, { 0.2926f, 0.3020f },
            { 0.2917f, 0.3011f }, { 0.2910f, 0.3001f }, { 0.2902f, 0.2992f }, { 0.2894f, 0.2983f }, { 0.2887f, 0.2975f }, { 0.2880f, 0.2966f },
            { 0.2873f, 0.2958f }, { 0.2866f, 0.2950f }, { 0.2860f, 0.2942f }, { 0.2853f, 0.2934f }, { 0.2847f, 0.2927f }, { 0.2841f, 0.2919f },
            { 0.2835f, 0.2912f }, { 0.2829f, 0.2905f }, { 0.2824f, 0.2898f }, { 0.2818f, 0.2891f }, { 0.2813f, 0.2884f }, { 0.2807f, 0.2878f },
            { 0.2802f, 0.2871f }, { 0.2797f, 0.2865f }, { 0.2792f, 0.2859f }, { 0.2788f, 0.2853f }, { 0.2783f, 0.2847f }, { 0.2778f, 0.2841f },
            { 0.2774f, 0.2836f }, { 0.2770f, 0.2830f }, { 0.2765f, 0.2825f }, { 0.2761f, 0.2819f }, { 0.2757f, 0.2814f }, { 0.2753f, 0.2809f },
            { 0.2749f, 0.2804f }, { 0.2745f, 0.2799f }, { 0.2742f, 0.2794f }, { 0.2738f, 0.2789f }, { 0.2734f, 0.2785f }, { 0.2731f, 0.2780f },
            { 0.2727f, 0.2776f }, { 0.2724f, 0.2771f }, { 0.2721f, 0.2767f }, { 0.2717f, 0.2763f }, { 0.2714f, 0.2758f }, { 0.2711f, 0.2754f },
            { 0.2708f, 0.2750f }, { 0.2705f, 0.2746f }, { 0.2702f, 0.2742f }, { 0.2699f, 0.2738f }, { 0.2696f, 0.2735f }, { 0.2694f, 0.2731f },
            { 0.2691f, 0.2727f }, { 0.2688f, 0.2724f }, { 0.2686f, 0.2720f }, { 0.2683f, 0.2717f }, { 0.2680f, 0.2713f }, { 0.2678f, 0.2710f },
            { 0.2675f, 0.2707f }, { 0.2673f, 0.2703f }, { 0.2671f, 0.2700f }, { 0.2668f, 0.2697f }, { 0.2666f, 0.2694f }, { 0.2664f, 0.2691f },
            { 0.2662f, 0.2688f }, { 0.2659f, 0.2685f }, { 0.2657f, 0.2682f }, { 0.2655f, 0.2679f }, { 0.2653f, 0.2676f }, { 0.2651f, 0.2673f },
            { 0.2649f, 0.2671f }, { 0.2647f, 0.2668f }, { 0.2645f, 0.2665f }, { 0.2643f, 0.2663f }, { 0.2641f, 0.2660f }, { 0.2639f, 0.2657f },
            { 0.2638f, 0.2655f }, { 0.2636f, 0.2652f }, { 0.2634f, 0.2650f }, { 0.2632f, 0.2648f }, { 0.2631f, 0.2645f }, { 0.2629f, 0.2643f },
            { 0.2627f, 0.2641f }, { 0.2626f, 0.2638f }, { 0.2624f, 0.2636f }, { 0.2622f, 0.2634f }, { 0.2621f, 0.2632f }, { 0.2619f, 0.2629f },
            { 0.2618f, 0.2627f }, { 0.2616f, 0.2625f }, { 0.2615f, 0.2623f }, { 0.2613f, 0.2621f }, { 0.2612f, 0.2619f }, { 0.2610f, 0.2617f },
            { 0.2609f, 0.2615f }, { 0.2608f, 0.2613f }, { 0.2606f, 0.2611f }, { 0.2605f, 0.2609f }, { 0.2604f, 0.2607f }, { 0.2602f, 0.2606f },
            { 0.2601f, 0.2604f }, { 0.2600f, 0.2602f }, { 0.2598f, 0.2600f }, { 0.2597f, 0.2598f }, { 0.2596f, 0.2597f }, { 0.2595f, 0.2595f },
            { 0.2593f, 0.2593f }, { 0.2592f, 0.2592f }, { 0.2591f, 0.2590f }, { 0.2590f, 0.2588f }, { 0.2589f, 0.2587f }, { 0.2588f, 0.2585f },
            { 0.2587f, 0.2584f }, { 0.2586f, 0.2582f }, { 0.2584f, 0.2580f }, { 0.2583f, 0.2579f }, { 0.2582f, 0.2577f }, { 0.2581f, 0.2576f },
            { 0.2580f, 0.2574f }, { 0.2579f, 0.2573f }, { 0.2578f, 0.2572f }, { 0.2577f, 0.2570f }, { 0.2576f, 0.2569f }, { 0.2575f, 0.2567f },
            { 0.2574f, 0.2566f }, { 0.2573f, 0.2565f }, { 0.2572f, 0.2563f }, { 0.2571f, 0.2562f }, { 0.2571f, 0.2561f }, { 0.2570f, 0.2559f },
            { 0.2569f, 0.2558f }, { 0.2568f, 0.2557f }, { 0.2567f, 0.2555f }, { 0.2566f, 0.2554f }, { 0.2565f, 0.2553f }, { 0.2564f, 0.2552f },
            { 0.2564f, 0.2550f }, { 0.2563f, 0.2549f }, { 0.2562f, 0.2548f }, { 0.2561f, 0.2547f }, { 0.2560f, 0.2546f }, { 0.2559f, 0.2545f },
            { 0.2559f, 0.2543f }, { 0.2558f, 0.2542f }, { 0.2557f, 0.2541f }, { 0.2556f, 0.2540f }, { 0.2556f, 0.2539f }, { 0.2555f, 0.2538f },
            { 0.2554f, 0.2537f }, { 0.2553f, 0.2536f }, { 0.2553f, 0.2535f }, { 0.2552f, 0.2534f }, { 0.2551f, 0.2533f }, { 0.2551f, 0.2532f },
            { 0.2550f, 0.2531f }, { 0.2549f, 0.2530f }, { 0.2548f, 0.2529f }, { 0.2548f, 0.2528f }, { 0.2547f, 0.2527f }, { 0.2546f, 0.2526f },
            { 0.2546f, 0.2525f }, { 0.2545f, 0.2524f }, { 0.2544f, 0.2523f }, { 0.2544f, 0.2522f }, { 0.2543f, 0.2521f }, { 0.2543f, 0.2520f },
            { 0.2542f, 0.2519f }, { 0.2541f, 0.2518f }, { 0.2541f, 0.2517f }, { 0.2540f, 0.2516f }, { 0.2540f, 0.2516f }, { 0.2539f, 0.2515f },
            { 0.2538f, 0.2514f }, { 0.2538f, 0.2513f }, { 0.2537f, 0.2512f }, { 0.2537f, 0.2511f }, { 0.2536f, 0.2511f }, { 0.2535f, 0.2510f },
            { 0.2535f, 0.2509f }, { 0.2534f, 0.2508f }, { 0.2534f, 0.2507f }, { 0.2533f, 0.2507f }, { 0.2533f, 0.2506f }, { 0.2532f, 0.2505f },
            { 0.2532f, 0.2504f }, { 0.2531f, 0.2503f }, { 0.2531f, 0.2503f }, { 0.2530f, 0.2502f }, { 0.2530f, 0.2501f }, { 0.2529f, 0.2500f },
            { 0.2529f, 0.2500f }, { 0.2528f, 0.2499f }, { 0.2528f, 0.2498f }, { 0.2527f, 0.2497f }, { 0.2527f, 0.2497f }, { 0.2526f, 0.2496f },
            { 0.2526f, 0.2495f }, { 0.2525f, 0.2495f }, { 0.2525f, 0.2494f }, { 0.2524f, 0.2493f }, { 0.2524f, 0.2493f }, { 0.2523f, 0.2492f },
            { 0.2523f, 0.2491f }, { 0.2523f, 0.2491f }, { 0.2522f, 0.2490f }, { 0.2522f, 0.2489f }, { 0.2521f, 0.2489f }, { 0.2521f, 0.2488f },
            { 0.2520f, 0.2487f }, { 0.2520f, 0.2487f }, { 0.2519f, 0.2486f }, { 0.2519f, 0.2485f }, { 0.2519f, 0.2485f }, { 0.2518f, 0.2484f },
            { 0.2518f, 0.2484f }, { 0.2517f, 0.2483f }, { 0.2517f, 0.2482f }, { 0.2517f, 0.2482f }, { 0.2516f, 0.2481f }, { 0.2516f, 0.2481f },
            { 0.2515f, 0.2480f }, { 0.2515f, 0.2480f }, { 0.2515f, 0.2479f }, { 0.2514f, 0.2478f }, { 0.2514f, 0.2478f }, { 0.2513f, 0.2477f },
            { 0.2513f, 0.2477f }, { 0.2513f, 0.2476f }, { 0.2512f, 0.2476f }, { 0.2512f, 0.2475f }, { 0.2512f, 0.2474f }, { 0.2511f, 0.2474f },
            { 0.2511f, 0.2473f }, { 0.2511f, 0.2473f }, { 0.2510f, 0.2472f }, { 0.2510f, 0.2472f }, { 0.2509f, 0.2471f }, { 0.2509f, 0.2471f },
            { 0.2509f, 0.2470f }, { 0.2508f, 0.2470f }, { 0.2508f, 0.2469f }, { 0.2508f, 0.2469f }, { 0.2507f, 0.2468f }, { 0.2507f, 0.2468f },
            { 0.2507f, 0.2467f }, { 0.2506f, 0.2467f }, { 0.2506f, 0.2466f }, { 0.2506f, 0.2466f }, { 0.2505f, 0.2465f }, { 0.2505f, 0.2465f },
            { 0.2505f, 0.2464f }, { 0.2505f, 0.2464f }, { 0.2504f, 0.2463f }, { 0.2504f, 0.2463f }, { 0.2504f, 0.2463f }, { 0.2503f, 0.2462f },
            { 0.2503f, 0.2462f }, { 0.2503f, 0.2461f }, { 0.2502f, 0.2461f }, { 0.2502f, 0.2460f }, { 0.2502f, 0.2460f }, { 0.2502f, 0.2459f },
            { 0.2501f, 0.2459f }, { 0.2501f, 0.2459f }, { 0.2501f, 0.2458f }, { 0.2500f, 0.2458f }, { 0.2500f, 0.2457f }, { 0.2500f, 0.2457f },
            { 0.2500f, 0.2456f }, { 0.2499f, 0.2456f }, { 0.2499f, 0.2456f }, { 0.2499f, 0.2455f }, { 0.2498f, 0.2455f }, { 0.2498f, 0.2454f },
            { 0.2498f, 0.2454f }, { 0.2498f, 0.2454f }, { 0.2497f, 0.2453f }, { 0.2497f, 0.2453f }, { 0.2497f, 0.2452f }, { 0.2497f, 0.2452f },
            { 0.2496f, 0.2452f }, { 0.2496f, 0.2451f }, { 0.2496f, 0.2451f }, { 0.2496f, 0.2450f }, { 0.2495f, 0.2450f }, { 0.2495f, 0.2450f },
            { 0.2495f, 0.2449f }, { 0.2495f, 0.2449f }, { 0.2494f, 0.2449f }, { 0.2494f, 0.2448f }, { 0.2494f, 0.2448f }, { 0.2494f, 0.2447f },
            { 0.2493f, 0.2447f }, { 0.2493f, 0.2447f }, { 0.2493f, 0.2446f }, { 0.2493f, 0.2446f }, { 0.2492f, 0.2446f }, { 0.2492f, 0.2445f },
            { 0.2492f, 0.2445f }, { 0.2492f, 0.2445f }, { 0.2491f, 0.2444f }, { 0.2491f, 0.2444f }, { 0.2491f, 0.2444f }, { 0.2491f, 0.2443f },
            { 0.2491f, 0.2443f }, { 0.2490f, 0.2443f }, { 0.2490f, 0.2442f }, { 0.2490f, 0.2442f }, { 0.2490f, 0.2442f }, { 0.2489f, 0.2441f },
            { 0.2489f, 0.2441f }, { 0.2489f, 0.2441f }, { 0.2489f, 0.2440f }, { 0.2489f, 0.2440f }, { 0.2488f, 0.2440f }, { 0.2488f, 0.2439f },
            { 0.2488f, 0.2439f }, { 0.2488f, 0.2439f }, { 0.2487f, 0.2438f } };
    // CIE 1964 10 degree Color Matching Functions (CMF)
    //  values of x,y from 1000 K to 40 000 K
    private static final float xyValue10Deg[][] = { { 0.6472f, 0.3506f }, { 0.6348f, 0.3612f }, { 0.6225f, 0.3710f }, { 0.6104f, 0.3797f },
            { 0.5983f, 0.3874f }, { 0.5864f, 0.3940f }, { 0.5747f, 0.3996f }, { 0.5632f, 0.4041f }, { 0.5519f, 0.4077f }, { 0.5408f, 0.4104f },
            { 0.5300f, 0.4123f }, { 0.5195f, 0.4134f }, { 0.5093f, 0.4139f }, { 0.4995f, 0.4138f }, { 0.4900f, 0.4132f }, { 0.4808f, 0.4122f },
            { 0.4720f, 0.4108f }, { 0.4636f, 0.4091f }, { 0.4555f, 0.4071f }, { 0.4478f, 0.4050f }, { 0.4404f, 0.4026f }, { 0.4333f, 0.4002f },
            { 0.4266f, 0.3976f }, { 0.4202f, 0.3950f }, { 0.4140f, 0.3923f }, { 0.4082f, 0.3895f }, { 0.4026f, 0.3868f }, { 0.3973f, 0.3841f },
            { 0.3922f, 0.3813f }, { 0.3873f, 0.3786f }, { 0.3827f, 0.3759f }, { 0.3783f, 0.3733f }, { 0.3741f, 0.3707f }, { 0.3701f, 0.3681f },
            { 0.3662f, 0.3656f }, { 0.3625f, 0.3631f }, { 0.3590f, 0.3607f }, { 0.3557f, 0.3583f }, { 0.3524f, 0.3560f }, { 0.3494f, 0.3538f },
            { 0.3464f, 0.3516f }, { 0.3436f, 0.3494f }, { 0.3409f, 0.3473f }, { 0.3383f, 0.3453f }, { 0.3358f, 0.3433f }, { 0.3334f, 0.3413f },
            { 0.3311f, 0.3394f }, { 0.3289f, 0.3376f }, { 0.3268f, 0.3358f }, { 0.3247f, 0.3341f }, { 0.3228f, 0.3324f }, { 0.3209f, 0.3307f },
            { 0.3190f, 0.3291f }, { 0.3173f, 0.3275f }, { 0.3156f, 0.3260f }, { 0.3140f, 0.3245f }, { 0.3124f, 0.3231f }, { 0.3109f, 0.3217f },
            { 0.3094f, 0.3203f }, { 0.3080f, 0.3190f }, { 0.3066f, 0.3177f }, { 0.3053f, 0.3164f }, { 0.3040f, 0.3152f }, { 0.3028f, 0.3140f },
            { 0.3016f, 0.3128f }, { 0.3004f, 0.3117f }, { 0.2993f, 0.3106f }, { 0.2982f, 0.3095f }, { 0.2971f, 0.3084f }, { 0.2961f, 0.3074f },
            { 0.2951f, 0.3064f }, { 0.2942f, 0.3054f }, { 0.2932f, 0.3044f }, { 0.2923f, 0.3035f }, { 0.2914f, 0.3026f }, { 0.2906f, 0.3017f },
            { 0.2897f, 0.3008f }, { 0.2889f, 0.3000f }, { 0.2881f, 0.2992f }, { 0.2874f, 0.2983f }, { 0.2866f, 0.2976f }, { 0.2859f, 0.2968f },
            { 0.2852f, 0.2960f }, { 0.2845f, 0.2953f }, { 0.2839f, 0.2946f }, { 0.2832f, 0.2939f }, { 0.2826f, 0.2932f }, { 0.2820f, 0.2925f },
            { 0.2813f, 0.2918f }, { 0.2808f, 0.2912f }, { 0.2802f, 0.2905f }, { 0.2796f, 0.2899f }, { 0.2791f, 0.2893f }, { 0.2785f, 0.2887f },
            { 0.2780f, 0.2881f }, { 0.2775f, 0.2876f }, { 0.2770f, 0.2870f }, { 0.2765f, 0.2865f }, { 0.2761f, 0.2859f }, { 0.2756f, 0.2854f },
            { 0.2751f, 0.2849f }, { 0.2747f, 0.2844f }, { 0.2743f, 0.2839f }, { 0.2738f, 0.2834f }, { 0.2734f, 0.2829f }, { 0.2730f, 0.2825f },
            { 0.2726f, 0.2820f }, { 0.2722f, 0.2816f }, { 0.2719f, 0.2811f }, { 0.2715f, 0.2807f }, { 0.2711f, 0.2803f }, { 0.2708f, 0.2799f },
            { 0.2704f, 0.2794f }, { 0.2701f, 0.2790f }, { 0.2697f, 0.2786f }, { 0.2694f, 0.2783f }, { 0.2691f, 0.2779f }, { 0.2688f, 0.2775f },
            { 0.2684f, 0.2771f }, { 0.2681f, 0.2768f }, { 0.2678f, 0.2764f }, { 0.2675f, 0.2761f }, { 0.2673f, 0.2757f }, { 0.2670f, 0.2754f },
            { 0.2667f, 0.2751f }, { 0.2664f, 0.2747f }, { 0.2662f, 0.2744f }, { 0.2659f, 0.2741f }, { 0.2656f, 0.2738f }, { 0.2654f, 0.2735f },
            { 0.2651f, 0.2732f }, { 0.2649f, 0.2729f }, { 0.2646f, 0.2726f }, { 0.2644f, 0.2723f }, { 0.2642f, 0.2720f }, { 0.2639f, 0.2718f },
            { 0.2637f, 0.2715f }, { 0.2635f, 0.2712f }, { 0.2633f, 0.2709f }, { 0.2631f, 0.2707f }, { 0.2629f, 0.2704f }, { 0.2626f, 0.2702f },
            { 0.2624f, 0.2699f }, { 0.2622f, 0.2697f }, { 0.2620f, 0.2694f }, { 0.2618f, 0.2692f }, { 0.2617f, 0.2690f }, { 0.2615f, 0.2687f },
            { 0.2613f, 0.2685f }, { 0.2611f, 0.2683f }, { 0.2609f, 0.2681f }, { 0.2607f, 0.2678f }, { 0.2606f, 0.2676f }, { 0.2604f, 0.2674f },
            { 0.2602f, 0.2672f }, { 0.2601f, 0.2670f }, { 0.2599f, 0.2668f }, { 0.2597f, 0.2666f }, { 0.2596f, 0.2664f }, { 0.2594f, 0.2662f },
            { 0.2593f, 0.2660f }, { 0.2591f, 0.2658f }, { 0.2590f, 0.2656f }, { 0.2588f, 0.2654f }, { 0.2587f, 0.2653f }, { 0.2585f, 0.2651f },
            { 0.2584f, 0.2649f }, { 0.2582f, 0.2647f }, { 0.2581f, 0.2645f }, { 0.2580f, 0.2644f }, { 0.2578f, 0.2642f }, { 0.2577f, 0.2640f },
            { 0.2575f, 0.2639f }, { 0.2574f, 0.2637f }, { 0.2573f, 0.2635f }, { 0.2572f, 0.2634f }, { 0.2570f, 0.2632f }, { 0.2569f, 0.2631f },
            { 0.2568f, 0.2629f }, { 0.2567f, 0.2628f }, { 0.2566f, 0.2626f }, { 0.2564f, 0.2625f }, { 0.2563f, 0.2623f }, { 0.2562f, 0.2622f },
            { 0.2561f, 0.2620f }, { 0.2560f, 0.2619f }, { 0.2559f, 0.2618f }, { 0.2558f, 0.2616f }, { 0.2557f, 0.2615f }, { 0.2555f, 0.2613f },
            { 0.2554f, 0.2612f }, { 0.2553f, 0.2611f }, { 0.2552f, 0.2609f }, { 0.2551f, 0.2608f }, { 0.2550f, 0.2607f }, { 0.2549f, 0.2606f },
            { 0.2548f, 0.2604f }, { 0.2547f, 0.2603f }, { 0.2546f, 0.2602f }, { 0.2546f, 0.2601f }, { 0.2545f, 0.2600f }, { 0.2544f, 0.2598f },
            { 0.2543f, 0.2597f }, { 0.2542f, 0.2596f }, { 0.2541f, 0.2595f }, { 0.2540f, 0.2594f }, { 0.2539f, 0.2593f }, { 0.2538f, 0.2591f },
            { 0.2537f, 0.2590f }, { 0.2537f, 0.2589f }, { 0.2536f, 0.2588f }, { 0.2535f, 0.2587f }, { 0.2534f, 0.2586f }, { 0.2533f, 0.2585f },
            { 0.2533f, 0.2584f }, { 0.2532f, 0.2583f }, { 0.2531f, 0.2582f }, { 0.2530f, 0.2581f }, { 0.2529f, 0.2580f }, { 0.2529f, 0.2579f },
            { 0.2528f, 0.2578f }, { 0.2527f, 0.2577f }, { 0.2526f, 0.2576f }, { 0.2526f, 0.2575f }, { 0.2525f, 0.2574f }, { 0.2524f, 0.2573f },
            { 0.2524f, 0.2572f }, { 0.2523f, 0.2572f }, { 0.2522f, 0.2571f }, { 0.2521f, 0.2570f }, { 0.2521f, 0.2569f }, { 0.2520f, 0.2568f },
            { 0.2519f, 0.2567f }, { 0.2519f, 0.2566f }, { 0.2518f, 0.2565f }, { 0.2517f, 0.2565f }, { 0.2517f, 0.2564f }, { 0.2516f, 0.2563f },
            { 0.2516f, 0.2562f }, { 0.2515f, 0.2561f }, { 0.2514f, 0.2560f }, { 0.2514f, 0.2560f }, { 0.2513f, 0.2559f }, { 0.2512f, 0.2558f },
            { 0.2512f, 0.2557f }, { 0.2511f, 0.2557f }, { 0.2511f, 0.2556f }, { 0.2510f, 0.2555f }, { 0.2510f, 0.2554f }, { 0.2509f, 0.2554f },
            { 0.2508f, 0.2553f }, { 0.2508f, 0.2552f }, { 0.2507f, 0.2551f }, { 0.2507f, 0.2551f }, { 0.2506f, 0.2550f }, { 0.2506f, 0.2549f },
            { 0.2505f, 0.2548f }, { 0.2505f, 0.2548f }, { 0.2504f, 0.2547f }, { 0.2503f, 0.2546f }, { 0.2503f, 0.2546f }, { 0.2502f, 0.2545f },
            { 0.2502f, 0.2544f }, { 0.2501f, 0.2544f }, { 0.2501f, 0.2543f }, { 0.2500f, 0.2542f }, { 0.2500f, 0.2542f }, { 0.2499f, 0.2541f },
            { 0.2499f, 0.2541f }, { 0.2499f, 0.2540f }, { 0.2498f, 0.2539f }, { 0.2498f, 0.2539f }, { 0.2497f, 0.2538f }, { 0.2497f, 0.2537f },
            { 0.2496f, 0.2537f }, { 0.2496f, 0.2536f }, { 0.2495f, 0.2536f }, { 0.2495f, 0.2535f }, { 0.2494f, 0.2534f }, { 0.2494f, 0.2534f },
            { 0.2493f, 0.2533f }, { 0.2493f, 0.2533f }, { 0.2493f, 0.2532f }, { 0.2492f, 0.2532f }, { 0.2492f, 0.2531f }, { 0.2491f, 0.2530f },
            { 0.2491f, 0.2530f }, { 0.2490f, 0.2529f }, { 0.2490f, 0.2529f }, { 0.2490f, 0.2528f }, { 0.2489f, 0.2528f }, { 0.2489f, 0.2527f },
            { 0.2488f, 0.2527f }, { 0.2488f, 0.2526f }, { 0.2488f, 0.2526f }, { 0.2487f, 0.2525f }, { 0.2487f, 0.2525f }, { 0.2487f, 0.2524f },
            { 0.2486f, 0.2524f }, { 0.2486f, 0.2523f }, { 0.2485f, 0.2523f }, { 0.2485f, 0.2522f }, { 0.2485f, 0.2522f }, { 0.2484f, 0.2521f },
            { 0.2484f, 0.2521f }, { 0.2484f, 0.2520f }, { 0.2483f, 0.2520f }, { 0.2483f, 0.2519f }, { 0.2482f, 0.2519f }, { 0.2482f, 0.2518f },
            { 0.2482f, 0.2518f }, { 0.2481f, 0.2517f }, { 0.2481f, 0.2517f }, { 0.2481f, 0.2516f }, { 0.2480f, 0.2516f }, { 0.2480f, 0.2516f },
            { 0.2480f, 0.2515f }, { 0.2479f, 0.2515f }, { 0.2479f, 0.2514f }, { 0.2479f, 0.2514f }, { 0.2478f, 0.2513f }, { 0.2478f, 0.2513f },
            { 0.2478f, 0.2513f }, { 0.2477f, 0.2512f }, { 0.2477f, 0.2512f }, { 0.2477f, 0.2511f }, { 0.2476f, 0.2511f }, { 0.2476f, 0.2510f },
            { 0.2476f, 0.2510f }, { 0.2476f, 0.2510f }, { 0.2475f, 0.2509f }, { 0.2475f, 0.2509f }, { 0.2475f, 0.2508f }, { 0.2474f, 0.2508f },
            { 0.2474f, 0.2508f }, { 0.2474f, 0.2507f }, { 0.2473f, 0.2507f }, { 0.2473f, 0.2506f }, { 0.2473f, 0.2506f }, { 0.2473f, 0.2506f },
            { 0.2472f, 0.2505f }, { 0.2472f, 0.2505f }, { 0.2472f, 0.2505f }, { 0.2471f, 0.2504f }, { 0.2471f, 0.2504f }, { 0.2471f, 0.2503f },
            { 0.2471f, 0.2503f }, { 0.2470f, 0.2503f }, { 0.2470f, 0.2502f }, { 0.2470f, 0.2502f }, { 0.2470f, 0.2502f }, { 0.2469f, 0.2501f },
            { 0.2469f, 0.2501f }, { 0.2469f, 0.2501f }, { 0.2468f, 0.2500f }, { 0.2468f, 0.2500f }, { 0.2468f, 0.2500f }, { 0.2468f, 0.2499f },
            { 0.2467f, 0.2499f }, { 0.2467f, 0.2498f }, { 0.2467f, 0.2498f }, { 0.2467f, 0.2498f }, { 0.2466f, 0.2497f }, { 0.2466f, 0.2497f },
            { 0.2466f, 0.2497f }, { 0.2466f, 0.2497f }, { 0.2465f, 0.2496f }, { 0.2465f, 0.2496f }, { 0.2465f, 0.2496f }, { 0.2465f, 0.2495f },
            { 0.2465f, 0.2495f }, { 0.2464f, 0.2495f }, { 0.2464f, 0.2494f }, { 0.2464f, 0.2494f }, { 0.2464f, 0.2494f }, { 0.2463f, 0.2493f },
            { 0.2463f, 0.2493f }, { 0.2463f, 0.2493f }, { 0.2463f, 0.2492f }, { 0.2462f, 0.2492f }, { 0.2462f, 0.2492f }, { 0.2462f, 0.2492f },
            { 0.2462f, 0.2491f }, { 0.2462f, 0.2491f }, { 0.2461f, 0.2491f }, { 0.2461f, 0.2490f }, { 0.2461f, 0.2490f }, { 0.2461f, 0.2490f },
            { 0.2460f, 0.2490f }, { 0.2460f, 0.2489f }, { 0.2460f, 0.2489f } };

    /**
     * Computes the CIE x,y coordinates of the black body at
     * the given temperature (in °K) and a viewing angle of 2 degrees.
     * 
     * The 2 vs 10 degree choice can be made based on how large the object's 
     * image on the retina is. Bigger means lower cone to rod ratio.
     * Thumb at arm's length is about 2 degrees; fist, 10.
     * @param temperature in °K
     * @return the CIE x,y coordinates of the equivalent black body.
     */
    public static Point2f getBlackBodyCoordinatesAt2Degrees(final float temperature) {
        return getCoordinatesForTemperature(temperature, xyValue2Deg);
    }

    /**
     * Computes the CIE x,y coordinates of the black body at
     * the given temperature (in °K) and a viewing angle of 10 degrees.
     * 
     * The 2 vs 10 degree choice can be made based on how large the object's 
     * image on the retina is. Bigger means lower cone to rod ratio.
     * Thumb at arm's length is about 2 degrees; fist, 10.
     * @param temperature in °K
     * @return the CIE x,y coordinates of the equivalent black body.
     */
    public static Point2f getBlackBodyCoordinatesAt10Degrees(float temperature) {
        return getCoordinatesForTemperature(temperature, xyValue10Deg);
    }

    private static Point2f getCoordinatesForTemperature(final float temperature, final float[][] table) {
        checkTemperature(temperature);
        if (temperature == MAX_TEMPERATURE) {
            final float[] lastDouble = table[table.length - 1];
            return new Point2f(lastDouble[0], lastDouble[1]);
        }
        final int step = (MAX_TEMPERATURE - MIN_TEMPERATURE) / (table.length - 1);
        final float index = (temperature - MIN_TEMPERATURE) / step;
        final int floorPart = (int) (index);
        final float floatPart = index - floorPart;
        final float x = getBarycentricValue(table[floorPart][0], table[floorPart + 1][0], floatPart);
        final float y = getBarycentricValue(table[floorPart][1], table[floorPart + 1][1], floatPart);        
        return new Point2f(x, y);
    }

    private static float getBarycentricValue(float base, float next, float value) {
        return base * (1 - value) + next * value;
    }

    private static void checkTemperature(final float temperature) {
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE)
            throw new IndexOutOfBoundsException("Temperature must be betwen " + MIN_TEMPERATURE + " and " + MAX_TEMPERATURE + "°K");
    }
}