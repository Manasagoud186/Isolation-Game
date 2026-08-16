package com.educhatbot.ai;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * ChatbotEngine - Educational Chatbot for Grade 1-10 School Syllabus
 * Acts as a virtual teacher following strict school curriculum guidelines
 */
public class ChatbotEngine {
    
    private Map<String, String[]> knowledgeBase;
    private Map<String, String[]> studyTips;
    private Map<String, String[]> examPreparation;
    private final Random random;

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "was", "were", "am", "be", "being", "been",
            "what", "why", "how", "when", "where", "who", "whom", "which",
            "define", "definition", "explain", "meaning", "tell", "me", "about",
            "of", "to", "in", "on", "for", "with", "and", "or", "but",
            "i", "you", "we", "they", "he", "she", "it", "my", "your", "our",
            "please", "can", "could", "would", "should", "do", "does", "did"
    ));
    
    public ChatbotEngine() {
        this.random = new Random();
        initializeKnowledgeBase();
        initializeStudyTips();
        initializeExamPreparation();
    }
    
    /**
     * Initialize comprehensive school syllabus knowledge base (Grade 1-10)
     */
    private void initializeKnowledgeBase() {
        knowledgeBase = new HashMap<>();
        
        // ========== MATHEMATICS (Grade 1-10) ==========
        
        // Number System
        knowledgeBase.put("whole numbers", new String[]{
            "Whole numbers are natural numbers including zero: 0, 1, 2, 3, 4, ...",
            "They start from 0 and go infinitely upward.",
            "Used for counting and ordering: 0, 1, 2, 3, 4, 5, ..."
        });
        
        knowledgeBase.put("integers", new String[]{
            "Integers include whole numbers and their negatives: ..., -3, -2, -1, 0, 1, 2, 3, ...",
            "They have no fractional or decimal parts.",
            "Number line: negative integers ← zero → positive integers"
        });
        
        knowledgeBase.put("rational numbers", new String[]{
            "Rational numbers can be written as a fraction p/q where p and q are integers (q ≠ 0).",
            "Examples: 1/2, -3/4, 5, 0.75 (which is 3/4)",
            "Decimals that end or repeat are rational numbers."
        });
        
        // Fractions & Decimals
        knowledgeBase.put("fraction definition", new String[]{
            "A fraction represents a part of a whole: numerator / denominator",
            "Example: 3/4 means 3 parts out of 4 equal parts.",
            "Numerator (top) = parts we have, Denominator (bottom) = total equal parts"
        });
        
        knowledgeBase.put("fraction operations", new String[]{
            "Addition: 1/4 + 2/4 = 3/4 (same denominator)",
            "Multiplication: 1/2 × 3/4 = 3/8 (multiply numerators and denominators)",
            "Division: 1/2 ÷ 1/4 = 2 (flip second fraction and multiply)"
        });
        
        knowledgeBase.put("decimal numbers", new String[]{
            "Decimals represent parts of a whole using place values: 0.5, 0.25, 3.14",
            "0.5 = 5/10 = 1/2; 0.25 = 25/100 = 1/4",
            "Add/subtract: align decimal points. Multiply: count decimal places in answer."
        });
        
        // LCM & HCF
        knowledgeBase.put("lcm least common multiple", new String[]{
            "LCM is the smallest number that is a multiple of two or more numbers.",
            "Example: Multiples of 4 are 4, 8, 12, 16, ... Multiples of 6 are 6, 12, 18, ...",
            "LCM(4, 6) = 12 (smallest common multiple)",
            "Find LCM: List multiples and pick the smallest one that appears in all lists."
        });
        
        knowledgeBase.put("hcf highest common factor", new String[]{
            "HCF (also GCD) is the largest number that divides two or more numbers.",
            "Example: Factors of 12 are 1, 2, 3, 4, 6, 12. Factors of 18 are 1, 2, 3, 6, 9, 18.",
            "HCF(12, 18) = 6 (largest common factor)",
            "Find HCF: List factors and pick the largest one that appears in all lists."
        });
        
        // Percentages
        knowledgeBase.put("percentage definition", new String[]{
            "Percentage means 'per 100' or 'out of 100'. Symbol: %",
            "50% = 50/100 = 0.5 (half)",
            "25% = 25/100 = 0.25 (quarter)",
            "100% = 1 (whole)"
        });
        
        knowledgeBase.put("percentage calculation", new String[]{
            "To find x% of a number N: (x/100) × N",
            "Example: 20% of 50 = (20/100) × 50 = 0.2 × 50 = 10",
            "To find what percentage: (Part/Whole) × 100",
            "Example: 25 out of 100 = (25/100) × 100 = 25%"
        });
        
        // Ratio & Proportion
        knowledgeBase.put("ratio definition", new String[]{
            "A ratio compares two quantities: a:b (read as 'a to b')",
            "Example: If there are 2 apples and 3 oranges, ratio = 2:3",
            "Ratio 2:3 means for every 2 apples, there are 3 oranges.",
            "Can be simplified: 4:6 = 2:3 (divide both by 2)"
        });
        
        knowledgeBase.put("proportion definition", new String[]{
            "A proportion states that two ratios are equal: a:b = c:d or a/b = c/d",
            "Example: 2:3 = 4:6 (both equal 2/3)",
            "Cross multiply to check: 2×6 = 3×4 → 12 = 12 ✓",
            "Used for scaling recipes, maps, and comparing quantities"
        });
        
        // Algebra
        knowledgeBase.put("algebra variables", new String[]{
            "Variables are letters (like x, y, a) that represent unknown numbers.",
            "Expression: 2x + 3 (has variable x)",
            "Equation: 2x + 3 = 11 (we solve for x)",
            "Coefficient: In 2x, the number 2 is the coefficient of x"
        });
        
        knowledgeBase.put("linear equations", new String[]{
            "Linear equation: highest power of variable is 1. Form: ax + b = c",
            "Example: 2x + 3 = 11",
            "Solve: 2x = 11 - 3 → 2x = 8 → x = 4",
            "Check: 2(4) + 3 = 8 + 3 = 11 ✓"
        });
        
        knowledgeBase.put("quadratic equations", new String[]{
            "Quadratic equation: highest power is 2. Form: ax² + bx + c = 0",
            "Example: x² + 3x + 2 = 0",
            "Solutions using quadratic formula: x = [-b ± √(b² - 4ac)] / (2a)",
            "Discriminant (b² - 4ac): If > 0 (two solutions), = 0 (one solution), < 0 (no real solutions)"
        });
        
        knowledgeBase.put("algebraic identities", new String[]{
            "(a + b)² = a² + 2ab + b²",
            "(a - b)² = a² - 2ab + b²",
            "a² - b² = (a + b)(a - b)",
            "(a + b)³ = a³ + 3a²b + 3ab² + b³"
        });
        
        // Geometry
        knowledgeBase.put("points lines angles", new String[]{
            "Point: A location with no size or dimension.",
            "Line: Straight path extending infinitely in both directions.",
            "Ray: Starts at a point and extends infinitely in one direction.",
            "Angle: Formed by two rays with a common endpoint (vertex).",
            "Angle types: Acute (< 90°), Right (= 90°), Obtuse (> 90°, < 180°), Straight (= 180°)"
        });
        
        knowledgeBase.put("triangles properties", new String[]{
            "Triangle: 3-sided polygon with 3 angles.",
            "Sum of all angles = 180°",
            "Types by sides: Equilateral (all equal), Isosceles (2 equal), Scalene (all different)",
            "Types by angles: Acute, Right, Obtuse",
            "Important: Pythagoras theorem for right triangles: a² + b² = c²"
        });
        
        knowledgeBase.put("circle properties", new String[]{
            "Circle: All points equidistant from center.",
            "Radius: Distance from center to any point on circle.",
            "Diameter: 2 × radius (longest chord through center)",
            "Circumference: 2πr or πd (perimeter of circle)",
            "Area: πr² (π ≈ 3.14 or 22/7)"
        });
        
        knowledgeBase.put("area rectangle", new String[]{
            "Formula: Area = length × breadth (or width)",
            "Example: Length = 5 cm, Breadth = 3 cm",
            "Area = 5 × 3 = 15 cm²"
        });
        
        knowledgeBase.put("area triangle", new String[]{
            "Formula: Area = ½ × base × height",
            "Example: Base = 6 cm, Height = 4 cm",
            "Area = ½ × 6 × 4 = 12 cm²",
            "Height must be perpendicular to base"
        });
        
        knowledgeBase.put("perimeter square", new String[]{
            "Perimeter: Distance around a shape.",
            "Square perimeter: P = 4 × side",
            "Example: Side = 5 cm, Perimeter = 4 × 5 = 20 cm",
            "Area of square = side² = 5² = 25 cm²"
        });
        
        // Trigonometry (basic)
        knowledgeBase.put("trigonometry basics", new String[]{
            "Trigonometry studies relationships between angles and sides in right triangles.",
            "Main ratios: sin, cos, tan",
            "sin(θ) = opposite / hypotenuse",
            "cos(θ) = adjacent / hypotenuse",
            "tan(θ) = opposite / adjacent",
            "Mnemonic: SOH-CAH-TOA"
        });
        
        knowledgeBase.put("trigonometry identities", new String[]{
            "sin²θ + cos²θ = 1 (Pythagorean identity)",
            "tanθ = sinθ / cosθ",
            "sin(90° - θ) = cosθ (complementary angles)",
            "cos(90° - θ) = sinθ"
        });
        
        // Statistics & Probability
        knowledgeBase.put("mean average", new String[]{
            "Mean: Sum of all values divided by number of values.",
            "Example: 5, 10, 15. Mean = (5 + 10 + 15) / 3 = 30 / 3 = 10",
            "Formula: Mean = Σ(values) / n"
        });
        
        knowledgeBase.put("median middle value", new String[]{
            "Median: Middle value when data is arranged in order.",
            "Example: 3, 5, 7, 9, 11. Median = 7 (middle value)",
            "Even numbers: Average the two middle values.",
            "Example: 3, 5, 7, 9. Median = (5 + 7) / 2 = 6"
        });
        
        knowledgeBase.put("mode most frequent", new String[]{
            "Mode: Value that appears most often in a dataset.",
            "Example: 2, 3, 3, 5, 5, 5, 7. Mode = 5 (appears 3 times)",
            "Can have no mode, one mode, or multiple modes."
        });
        
        knowledgeBase.put("probability basics", new String[]{
            "Probability: Chance of an event happening (0 to 1).",
            "Formula: P(event) = favorable outcomes / total outcomes",
            "Example: Probability of rolling a 3 on a die = 1/6 ≈ 0.167",
            "P(event) = 0: impossible, P(event) = 1: certain"
        });
        
        knowledgeBase.put("simple interest formula", new String[]{
            "Formula: Simple Interest (SI) = (P × R × T) / 100",
            "P = Principal (initial amount)",
            "R = Rate of interest (% per year)",
            "T = Time (in years)",
            "Example: P=1000, R=5%, T=2 years. SI = (1000 × 5 × 2) / 100 = 100"
        });
        
        // ========== PHYSICS ==========
        knowledgeBase.put("speed formula", new String[]{
            "Formula: Speed = Distance / Time",
            "Unit: meters per second (m/s) or kilometers per hour (km/h)",
            "Example: Distance = 100 m, Time = 10 s. Speed = 100/10 = 10 m/s",
            "Average speed = Total distance / Total time"
        });
        
        knowledgeBase.put("force definition", new String[]{
            "Force: A push or pull that changes motion or shape.",
            "Unit: Newton (N)",
            "Newton's Second Law: Force = mass × acceleration (F = ma)",
            "Example: mass = 5 kg, acceleration = 2 m/s². Force = 5 × 2 = 10 N"
        });
        
        knowledgeBase.put("work formula", new String[]{
            "Work: Force applied over a distance.",
            "Formula: Work = Force × Distance (W = F × d)",
            "Unit: Joule (J)",
            "Example: Force = 10 N, Distance = 5 m. Work = 10 × 5 = 50 J"
        });
        
        knowledgeBase.put("power formula", new String[]{
            "Power: Rate of doing work (energy per unit time).",
            "Formula: Power = Work / Time (P = W / t)",
            "Unit: Watt (W)",
            "Example: Work = 100 J, Time = 5 s. Power = 100/5 = 20 W"
        });
        
        knowledgeBase.put("light reflection", new String[]{
            "Law of reflection: Angle of incidence = Angle of reflection",
            "Incident ray: incoming light ray",
            "Reflected ray: bounced light ray",
            "Normal: perpendicular line at point of incidence",
            "Both angles measured from normal."
        });
        
        knowledgeBase.put("light refraction", new String[]{
            "Refraction: Bending of light when passing between different media.",
            "Light bends toward normal when entering denser medium (slower).",
            "Light bends away from normal when leaving denser medium.",
            "Example: Straw looks bent in water due to refraction."
        });
        
        knowledgeBase.put("sound waves", new String[]{
            "Sound: Mechanical wave that travels through matter.",
            "Speed of sound: ~340 m/s in air (varies with temperature)",
            "Higher frequency = higher pitch",
            "Larger amplitude = louder sound",
            "Sound needs a medium to travel (unlike light)."
        });
        
        knowledgeBase.put("electricity basics", new String[]{
            "Electric current: Flow of electric charge.",
            "Unit: Ampere (A)",
            "Voltage: Electrical pressure pushing current.",
            "Unit: Volt (V)",
            "Resistance: Opposition to current flow.",
            "Unit: Ohm (Ω)"
        });
        
        knowledgeBase.put("ohms law", new String[]{
            "Formula: V = I × R",
            "V = Voltage (Volts)",
            "I = Current (Amperes)",
            "R = Resistance (Ohms)",
            "Example: Voltage = 12V, Resistance = 4Ω. Current = 12/4 = 3A"
        });
        
        // ========== BIOLOGY ==========
        knowledgeBase.put("cell structure", new String[]{
            "Cell: Basic unit of life.",
            "All living things are made of cells.",
            "Plant cells have: nucleus, cell wall, chloroplasts, vacuole",
            "Animal cells have: nucleus, cell membrane (no cell wall or chloroplasts)",
            "Nucleus: Contains DNA, controls cell activities"
        });
        
        knowledgeBase.put("photosynthesis", new String[]{
            "Photosynthesis: Process where plants make food using sunlight.",
            "Equation: 6CO₂ + 6H₂O + sunlight → C₆H₁₂O₆ (glucose) + 6O₂",
            "Location: Chloroplasts in leaf cells",
            "Chlorophyll: Green pigment that captures sunlight",
            "Products: Glucose (food) and Oxygen (released to air)"
        });
        
        knowledgeBase.put("respiration", new String[]{
            "Cellular respiration: Process where cells release energy from food.",
            "Aerobic (with oxygen): Glucose + O₂ → CO₂ + H₂O + Energy",
            "Anaerobic (without oxygen): Produces lactic acid or ethanol",
            "Happens in mitochondria",
            "Energy released is used for all cell activities"
        });
        
        knowledgeBase.put("human digestive", new String[]{
            "Path: Mouth → Esophagus → Stomach → Small intestine → Large intestine → Rectum",
            "Mouth: Chewing and saliva mixes with food",
            "Stomach: Acids break down food further",
            "Small intestine: Most nutrient absorption happens here",
            "Large intestine: Water absorption, waste formation"
        });
        
        knowledgeBase.put("nutrition", new String[]{
            "Plant nutrition: Autotrophic (make own food through photosynthesis)",
            "Animal nutrition: Heterotrophic (eat plants or other animals)",
            "Herbivores: Eat only plants",
            "Carnivores: Eat only meat",
            "Omnivores: Eat both plants and meat"
        });
        
        knowledgeBase.put("mitosis", new String[]{
            "Mitosis: Cell division producing two identical daughter cells.",
            "Used for growth and repair.",
            "Stages: Prophase → Metaphase → Anaphase → Telophase",
            "Mnemonic: PMAT",
            "Each daughter cell is identical to parent cell"
        });
        
        knowledgeBase.put("reproduction", new String[]{
            "Asexual: One parent, offspring identical (binary fission, budding)",
            "Sexual: Two parents, genetic mixing (sperm + egg = zygote)",
            "Human reproduction: Ovum (egg) + Sperm = Embryo",
            "Gestation period in humans: ~9 months"
        });
        
        knowledgeBase.put("ecosystem", new String[]{
            "Ecosystem: Community of living organisms + environment.",
            "Food chain: Producer → Consumer → Decomposer",
            "Example: Plant → Grasshopper → Bird → Snake",
            "Energy flows: Sun → Plants → Animals",
            "Food web: Multiple interconnected food chains"
        });
        
        // ========== CHEMISTRY ==========
        knowledgeBase.put("matter definition", new String[]{
            "Matter: Anything that has mass and occupies space.",
            "Everything around us is made of matter.",
            "Properties: Physical (color, shape) and Chemical (reactivity)"
        });
        
        knowledgeBase.put("states of matter", new String[]{
            "Solid: Fixed shape, fixed volume. Particles tightly packed.",
            "Liquid: No fixed shape, fixed volume. Particles loosely packed.",
            "Gas: No fixed shape, no fixed volume. Particles very spread out.",
            "Plasma: Fourth state - ionized gas (exists in stars)",
            "Changes: Melting, Freezing, Evaporation, Condensation, Sublimation"
        });
        
        knowledgeBase.put("elements compounds mixtures", new String[]{
            "Element: Pure substance made of one type of atom. Example: Oxygen (O), Gold (Au)",
            "Compound: Pure substance made of 2+ elements chemically bonded. Example: Water (H₂O)",
            "Mixture: Combination of 2+ substances (not chemically bonded). Example: Salt + Sand",
            "Mixtures can be separated by physical methods."
        });
        
        knowledgeBase.put("acids bases", new String[]{
            "Acid: Sour taste, turns blue litmus red. Example: HCl, H₂SO₄",
            "Base: Bitter taste, slippery, turns red litmus blue. Example: NaOH, KOH",
            "Salt: Neutral pH, formed from acid-base reaction.",
            "Neutralization: Acid + Base → Salt + Water"
        });
        
        knowledgeBase.put("metals nonmetals", new String[]{
            "Metals: Good conductors of heat/electricity, shiny, malleable, ductile. Example: Iron, Copper",
            "Non-metals: Poor conductors, usually dull, brittle. Example: Carbon, Sulfur",
            "Metalloids: Properties between metals and non-metals. Example: Silicon"
        });
        
        knowledgeBase.put("chemical reactions", new String[]{
            "Chemical reaction: Atoms rearrange to form new substances.",
            "Types: Synthesis (A+B→AB), Decomposition (AB→A+B), Single replacement, Double replacement",
            "Combustion: Substance + O₂ → Products (releases heat/light)",
            "Indicators: Color change, gas formation, temperature change, or precipitate formation"
        });
        
        knowledgeBase.put("rusting", new String[]{
            "Rusting: Chemical reaction of iron with oxygen and moisture.",
            "Equation: 4Fe + 3O₂ + 6H₂O → 4Fe(OH)₃ (rust)",
            "Prevention: Paint, oil, galvanization, stainless steel",
            "Rusting is oxidation - irreversible chemical change"
        });
        
        // ========== ENGLISH GRAMMAR & LITERATURE ==========
        knowledgeBase.put("noun", new String[]{
            "Noun: Word that names a person, place, thing, or idea.",
            "Examples: teacher (person), school (place), book (thing), courage (idea)",
            "Proper nouns: Specific names (India, John). Common nouns: General names (country, boy)"
        });
        
        knowledgeBase.put("verb", new String[]{
            "Verb: Word that shows action, occurrence, or state of being.",
            "Action verbs: run, jump, eat, write, sing",
            "Linking verbs: is, are, was, were, am, be",
            "Helping verbs: can, could, will, would, should, have, has"
        });
        
        knowledgeBase.put("adjective", new String[]{
            "Adjective: Word that describes or modifies a noun.",
            "Examples: big, blue, happy, tall, beautiful, broken",
            "Position: Usually before the noun.",
            "Example: 'The tall boy' - 'tall' is adjective describing 'boy'"
        });
        
        knowledgeBase.put("adverb", new String[]{
            "Adverb: Word that modifies a verb, adjective, or other adverb.",
            "Often ends in -ly: quickly, slowly, carefully, happily",
            "Answers: How? When? Where? How often?",
            "Example: 'She ran quickly' - 'quickly' tells how she ran"
        });
        
        knowledgeBase.put("preposition", new String[]{
            "Preposition: Word showing relationship between noun and other words.",
            "Common: in, on, at, under, over, between, during, after, before",
            "Examples: in the box (location), on Monday (time), between friends (relationship)"
        });
        
        knowledgeBase.put("pronoun", new String[]{
            "Pronoun: Word replacing a noun.",
            "Personal pronouns: I, you, he, she, it, we, they",
            "Example: 'John is tall. He is smart.' (He replaces John)",
            "Demonstrative: this, that, these, those"
        });
        
        knowledgeBase.put("tense simple", new String[]{
            "Simple Present: I/you/we/they play, he/she/it plays",
            "Simple Past: I/you/he/she/it/we/they played",
            "Simple Future: I/you/he/she/it/we/they will play",
            "Uses: Habits, facts, routines, completed actions, future plans"
        });
        
        knowledgeBase.put("active passive", new String[]{
            "Active voice: Subject performs the action.",
            "Example: 'The boy kicked the ball.' (boy is subject doing action)",
            "Passive voice: Subject receives the action.",
            "Example: 'The ball was kicked by the boy.' (ball is subject receiving action)"
        });
        
        // ========== SOCIAL STUDIES ==========
        
        // History
        knowledgeBase.put("ancient civilizations", new String[]{
            "Major ancient civilizations:",
            "- Egyptian: Nile River valley, pyramids, pharaohs",
            "- Mesopotamian: Between Tigris and Euphrates, Sumerians",
            "- Indus Valley: Modern Pakistan-India, planned cities",
            "- Chinese: Yellow River, Great Wall, dynasties",
            "- Greek: Democracy, philosophy, Olympics"
        });
        
        knowledgeBase.put("indian independence", new String[]{
            "Leaders: Mahatma Gandhi, Jawaharlal Nehru, Sardar Patel",
            "Non-violent resistance against British rule",
            "Swadeshi movement, Quit India Movement",
            "Independence: August 15, 1947"
        });
        
        // Geography
        knowledgeBase.put("earth solar", new String[]{
            "Earth: Third planet from Sun, only known planet with life",
            "Sun: Center of solar system, provides light and heat",
            "Eight planets: Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune",
            "Moon: Earth's natural satellite, causes tides"
        });
        
        knowledgeBase.put("climate weather", new String[]{
            "Weather: Short-term atmospheric conditions (hot/cold, rainy/dry)",
            "Climate: Long-term weather pattern of a region",
            "Seasons: Earth's tilted axis causes seasonal changes",
            "Tropical, Temperate, Polar climates"
        });
        
        knowledgeBase.put("natural resources", new String[]{
            "Renewable: Water, soil, forests, sunlight, wind (can be replenished)",
            "Non-renewable: Coal, oil, natural gas, minerals (cannot be replaced quickly)",
            "Conservation: Sustainable use to preserve for future generations"
        });
        
        // Civics
        knowledgeBase.put("constitution india", new String[]{
            "Constitution: Supreme law of India, adopted January 26, 1950",
            "Written by: Dr. Ambedkar (chief architect)",
            "Features: Democratic, Secular, Republic",
            "Contains Fundamental Rights and Duties"
        });
        
        knowledgeBase.put("fundamental rights india", new String[]{
            "Right to Equality: Equal before law, no discrimination",
            "Right to Freedom: Speech, expression, assembly, movement",
            "Right against Exploitation: No forced labor, child labor banned",
            "Right to Freedom of Religion: Choose and practice religion",
            "Cultural and Educational Rights"
        });
        
        knowledgeBase.put("government india", new String[]{
            "Three branches: Executive, Legislative, Judiciary",
            "Executive: President, Vice President, Council of Ministers",
            "Legislative: Lok Sabha (lower) + Rajya Sabha (upper)",
            "Judiciary: Supreme Court, High Courts, Lower Courts"
        });
        
        // Economics
        knowledgeBase.put("economics", new String[]{
            "Economy: System of production, distribution, and consumption of goods",
            "Goods: Tangible products (food, clothes, cars)",
            "Services: Intangible offerings (education, healthcare, transport)",
            "Wants vs Needs: Needs are essential, wants are desires"
        });
        
        knowledgeBase.put("money currency", new String[]{
            "Money: Medium of exchange for goods and services.",
            "Indian currency: Rupee (₹)",
            "Functions: Medium of exchange, store of value, measure of value",
            "1 Rupee = 100 Paise"
        });
        
        // ========== COMPUTER SCIENCE ==========
        knowledgeBase.put("computer hardware", new String[]{
            "Computer: Electronic device that processes data.",
            "Hardware: Physical components (keyboard, monitor, CPU, RAM)",
            "CPU: Central Processing Unit, brain of computer",
            "RAM: Temporary memory, loses data when powered off",
            "Storage: HDD, SSD (permanent storage)"
        });
        
        knowledgeBase.put("input output", new String[]{
            "Input devices: keyboard, mouse, scanner, microphone",
            "Output devices: monitor, printer, speaker",
            "Processing: CPU processes the data",
            "Storage: Save data permanently"
        });
        
        knowledgeBase.put("operating system", new String[]{
            "OS: Software managing computer resources.",
            "Examples: Windows, macOS, Linux, Android, iOS",
            "Functions: Manage files, run programs, control hardware",
            "Interface: GUI or CLI"
        });
        
        knowledgeBase.put("internet", new String[]{
            "Internet: Global network of computers connected via protocols.",
            "www: World Wide Web - information system on Internet",
            "Website: Collection of web pages",
            "Browser: Software to view websites (Chrome, Firefox, Edge)",
            "Email: Electronic mail for sending messages"
        });
        
        knowledgeBase.put("cyber safety", new String[]{
            "Passwords: Keep strong and private, don't share",
            "Phishing: Fake emails trying to steal information - AVOID",
            "Viruses: Malicious programs - use antivirus software",
            "Identity theft: Protect personal information online",
            "Tips: Don't click unknown links, verify websites, enable 2FA"
        });
        
        knowledgeBase.put("programming", new String[]{
            "Programming: Writing instructions for computers.",
            "Variables: Storage for data (name, age, score)",
            "Data types: Numbers, text (strings), true/false (boolean)",
            "Loops: Repeat actions (for loop, while loop)",
            "Conditionals: if/else - make decisions"
        });
        
        knowledgeBase.put("pythagorean theorem", new String[]{
            "The Pythagorean theorem states: a² + b² = c² in a right triangle.",
            "Where 'a' and 'b' are the lengths of the two legs, and 'c' is the hypotenuse.",
            "Example: A triangle with sides 3, 4, and 5 satisfies this theorem (9 + 16 = 25)."
        });

        knowledgeBase.put("prime numbers", new String[]{
            "A prime number is a whole number greater than 1 that has exactly two factors: 1 and itself.",
            "Examples: 2, 3, 5, 7, 11 are prime. 1 is not prime.",
            "A number is composite if it has more than two factors (example: 12 has factors 1, 2, 3, 4, 6, 12)."
        });

        knowledgeBase.put("factors and multiples", new String[]{
            "A factor divides a number exactly (e.g., 3 is a factor of 12 because 12 ÷ 3 = 4).",
            "A multiple is the result of multiplying a number (e.g., 12 is a multiple of 3 because 3 × 4 = 12).",
            "To find factors: list numbers that divide evenly. To find multiples: keep multiplying by 1, 2, 3, ..."
        });

        knowledgeBase.put("percentage", new String[]{
            "Percentage means 'per 100'. For example, 25% means 25 out of 100.",
            "To find x% of a number N: (x/100) × N.",
            "Example: 20% of 50 = (20/100) × 50 = 10."
        });

        knowledgeBase.put("linear equations", new String[]{
            "A linear equation is an equation where the highest power of the variable is 1 (like 2x + 3 = 11).",
            "To solve: do the same operation on both sides to isolate the variable.",
            "Example: 2x + 3 = 11 → 2x = 8 → x = 4."
        });

        knowledgeBase.put("mean median mode", new String[]{
            "Mean is the average: add all values and divide by the number of values.",
            "Median is the middle value after sorting (or average of two middle values if count is even).",
            "Mode is the value that appears most often."
        });
        
        // Science
        knowledgeBase.put("what is physics", new String[]{
            "Physics is the science of matter, energy, and forces.",
            "It studies motion, light, electricity, magnetism, and atomic structure.",
            "Physics helps explain how the universe works."
        });
        
        knowledgeBase.put("what is chemistry", new String[]{
            "Chemistry is the study of substances, reactions, and transformations of matter.",
            "Key topics include: atoms, molecules, elements, compounds, and chemical reactions.",
            "Chemistry connects physics to biology and is essential for understanding materials."
        });
        
        knowledgeBase.put("what is biology", new String[]{
            "Biology is the science of life and living organisms.",
            "Major branches include: molecular biology, genetics, ecology, and evolution.",
            "Biology helps us understand living systems and health."
        });
        
        knowledgeBase.put("photosynthesis", new String[]{
            "Photosynthesis is the process by which plants convert light into chemical energy.",
            "The equation is: 6CO₂ + 6H₂O + light → C₆H₁₂O₆ + 6O₂",
            "This process is essential for producing oxygen and glucose for plant growth."
        });

        knowledgeBase.put("newtons laws", new String[]{
            "Newton's First Law: An object stays at rest or in uniform motion unless acted on by an external force.",
            "Newton's Second Law: Force = mass × acceleration (F = ma).",
            "Newton's Third Law: For every action, there is an equal and opposite reaction."
        });

        knowledgeBase.put("states of matter", new String[]{
            "Common states of matter are solid, liquid, and gas (and plasma at very high energy).",
            "Solids have fixed shape and volume; liquids have fixed volume but take the container's shape; gases have neither fixed shape nor volume.",
            "Changes of state include melting, freezing, evaporation/boiling, condensation, and sublimation."
        });

        knowledgeBase.put("atomic structure", new String[]{
            "An atom has a nucleus (protons and neutrons) and electrons around it.",
            "Protons are positive, electrons are negative, neutrons have no charge.",
            "Atomic number = number of protons; mass number = protons + neutrons."
        });

        knowledgeBase.put("cell", new String[]{
            "A cell is the basic structural and functional unit of life.",
            "Plant cells typically have a cell wall and chloroplasts; animal cells do not.",
            "Key parts include nucleus, cytoplasm, and cell membrane; organelles do specific jobs inside the cell."
        });

        knowledgeBase.put("mitosis", new String[]{
            "Mitosis is the process where one cell divides to form two genetically identical daughter cells.",
            "It is used for growth, repair, and asexual reproduction.",
            "Main stages are often taught as: prophase, metaphase, anaphase, telophase (PMAT)."
        });
        
        // History & Geography
        knowledgeBase.put("what is history", new String[]{
            "History is the study of past events and human civilizations.",
            "It helps us understand how societies developed and shaped the world.",
            "Key areas: ancient civilizations, medieval period, modern era, and contemporary events."
        });
        
        knowledgeBase.put("what is geography", new String[]{
            "Geography is the study of Earth's landscapes, populations, and environments.",
            "It includes physical geography (landforms, climate) and human geography (cultures, economies).",
            "Geography helps us understand global patterns and human-environment interactions."
        });
        
        // Literature & Languages
        knowledgeBase.put("what is literature", new String[]{
            "Literature is the collection of written works expressing human experiences and ideas.",
            "Forms include: novels, poetry, drama, essays, and short stories.",
            "Literature provides insights into cultures, emotions, and the human condition."
        });

        knowledgeBase.put("noun", new String[]{
            "A noun is a word that names a person, place, thing, or idea.",
            "Examples: teacher (person), city (place), book (thing), happiness (idea).",
            "Nouns can be common/proper, singular/plural, countable/uncountable."
        });

        knowledgeBase.put("verb", new String[]{
            "A verb is a word that shows an action, occurrence, or state of being.",
            "Examples: run (action), become (occurrence), is (state of being).",
            "Verbs change form based on tense: present, past, future."
        });

        knowledgeBase.put("adjective", new String[]{
            "An adjective describes or modifies a noun or pronoun.",
            "Examples: tall building, blue sky, interesting book.",
            "Adjectives answer questions like: which one? what kind? how many?"
        });

        knowledgeBase.put("parts of speech", new String[]{
            "Parts of speech are categories of words based on how they are used in a sentence.",
            "Common parts of speech: noun, pronoun, verb, adjective, adverb, preposition, conjunction, interjection.",
            "Example: 'The quick fox runs quickly' → quick (adjective), fox (noun), runs (verb), quickly (adverb)."
        });

        knowledgeBase.put("active and passive voice", new String[]{
            "Active voice: the subject does the action (e.g., 'The boy kicked the ball').",
            "Passive voice: the subject receives the action (e.g., 'The ball was kicked by the boy').",
            "Passive voice often uses a form of 'be' + past participle (was eaten, is written)."
        });

        knowledgeBase.put("simple present tense", new String[]{
            "Simple present tense is used for habits, routines, facts, and general truths.",
            "Examples: 'I walk to school.' 'Water boils at 100°C.'",
            "With he/she/it, verbs often add -s/-es: 'She plays', 'He goes'."
        });

        knowledgeBase.put("computer", new String[]{
            "A computer is an electronic device that takes input, processes it, stores data, and produces output.",
            "Basic parts: CPU (processing), memory/RAM (temporary), storage (HDD/SSD), input devices (keyboard/mouse), output devices (monitor/printer).",
            "Software is the programs; hardware is the physical parts."
        });

        knowledgeBase.put("internet", new String[]{
            "The Internet is a global network of computers connected to share information.",
            "A website is a collection of web pages; a browser (Chrome/Edge) is used to access websites.",
            "Safety tip: never share passwords/OTP and be careful with unknown links."
        });

        knowledgeBase.put("what is democracy", new String[]{
            "Democracy is a system of government where people choose their leaders through elections.",
            "Key ideas include rule of law, equality, rights and responsibilities, and participation.",
            "Democracy can be direct (people vote on issues) or representative (people elect representatives)."
        });

        knowledgeBase.put("fundamental rights", new String[]{
            "Fundamental rights are basic rights guaranteed to citizens by a country's constitution.",
            "They protect freedoms like equality, speech, education, and protection from discrimination.",
            "Rights come with responsibilities: respecting others' rights and following laws."
        });
        
        // General Academic
        knowledgeBase.put("critical thinking", new String[]{
            "Critical thinking is the ability to analyze, evaluate, and reason through information.",
            "It involves questioning assumptions, identifying biases, and solving problems effectively.",
            "Essential skills include: observation, analysis, inference, and reflection."
        });
        
        knowledgeBase.put("research", new String[]{
            "Research is systematic investigation to discover new knowledge.",
            "Key steps: define question, literature review, methodology, data collection, analysis.",
            "Good research requires careful planning, ethical considerations, and documentation."
        });
        
        // Grade 10 Mathematics
        knowledgeBase.put("quadratic equations", new String[]{
            "A quadratic equation is of the form ax² + bx + c = 0 where a ≠ 0.",
            "Solutions are given by the quadratic formula: x = [-b ± sqrt(b² - 4ac)] / (2a).",
            "The discriminant D = b² - 4ac determines the nature of roots: D>0 (two real), D=0 (one real), D<0 (no real roots)."
        });
        
        knowledgeBase.put("coordinate geometry", new String[]{
            "Coordinate geometry represents geometric shapes using coordinates on a plane (x, y).",
            "Distance between two points (x1, y1) and (x2, y2) = sqrt((x2-x1)² + (y2-y1)²).",
            "Slope of a line joining (x1, y1) and (x2, y2) = (y2 - y1) / (x2 - x1)."
        });
        
        knowledgeBase.put("trigonometry basics", new String[]{
            "Trigonometry studies relationships between angles and sides of triangles.",
            "Main ratios: sin(θ) = opposite/hypotenuse, cos(θ) = adjacent/hypotenuse, tan(θ) = opposite/adjacent.",
            "Key identities: sin²θ + cos²θ = 1, tanθ = sinθ/cosθ."
        });
        
        knowledgeBase.put("probability", new String[]{
            "Probability measures the chance of an event occurring, from 0 (impossible) to 1 (certain).",
            "Basic formula: P(event) = (number of favorable outcomes) / (total possible outcomes).",
            "For independent events, P(A and B) = P(A) × P(B)."
        });
        
        knowledgeBase.put("statistics", new String[]{
            "Statistics collects, analyzes, and interprets data to make informed decisions.",
            "Measures of central tendency: mean (average), median (middle value), mode (most frequent).",
            "Measures of spread: range (max-min), variance, and standard deviation."
        });
        
        knowledgeBase.put("matrices", new String[]{
            "A matrix is a rectangular array of numbers arranged in rows and columns.",
            "Addition/subtraction: element-wise for same-sized matrices. Multiplication: rows × columns rule.",
            "Applications: solving systems of equations, transformations, and data representation."
        });
        
        // Grade 10 Science
        knowledgeBase.put("chemical reactions", new String[]{
            "A chemical reaction involves rearrangement of atoms to form new substances.",
            "Types: synthesis (A+B→AB), decomposition (AB→A+B), single replacement (A+BC→AC+B), double replacement (AB+CD→AD+CB).",
            "Indicators include color change, gas formation, temperature change, or precipitate formation."
        });
        
        knowledgeBase.put("acid base reaction", new String[]{
            "Acids produce H+ ions in water; bases produce OH- ions.",
            "Neutralization: acid + base → salt + water (e.g., HCl + NaOH → NaCl + H₂O).",
            "Indicators: litmus turns red in acid, blue in base; phenolphthalein is colorless in acid, pink in base."
        });
        
        knowledgeBase.put("electricity basics", new String[]{
            "Electric current is the flow of charge, measured in amperes (A). Voltage pushes current, measured in volts (V).",
            "Ohm's Law: V = I × R (voltage = current × resistance). Resistance opposes current, measured in ohms (Ω).",
            "Series circuits: same current through all components; parallel circuits: same voltage across all components."
        });
        
        knowledgeBase.put("magnetism", new String[]{
            "Magnetism is a force caused by moving charges or magnetic materials. Magnets have north (N) and south (S) poles.",
            "Like poles repel; opposite poles attract. Magnetic field lines flow from N to S.",
            "Electromagnetism: electric current produces a magnetic field; a changing magnetic field can induce current (Faraday's law)."
        });
        
        knowledgeBase.put("light and optics", new String[]{
            "Light travels in straight lines (rectilinear propagation) and at different speeds in different media.",
            "Reflection: angle of incidence equals angle of reflection. Refraction: bending of light due to change in speed.",
            "Lenses focus or diverge light. Convex lenses converge; concave lenses diverge."
        });
        
        // Grade 10 Social Science
        knowledgeBase.put("industrial revolution", new String[]{
            "The Industrial Revolution began in Britain in the late 18th century, transforming production from hand to machine.",
            "Key inventions include the steam engine, spinning jenny, and power loom, boosting textiles and manufacturing.",
            "It led to urbanization, new social classes, and set the stage for modern industrial societies."
        });
        
        knowledgeBase.put("nationalism", new String[]{
            "Nationalism is the belief that people who share a common culture, history, and language should form their own nation.",
            "It inspired movements for independence and unification in Europe and elsewhere in the 19th century.",
            "Nationalism can promote unity but also lead to conflicts when different groups claim the same territory."
        });
        
        knowledgeBase.put("world wars", new String[]{
            "World War I (1914–1918) was sparked by assassination, alliances, imperialism, militarism, and nationalism.",
            "World War II (1939–1945) involved Axis powers (Germany, Italy, Japan) vs. Allies (Britain, USSR, US, China).",
            "The wars reshaped borders, led to the Cold War, decolonization, and the creation of the United Nations."
        });
        
        knowledgeBase.put("globalization", new String[]{
            "Globalization is the increasing interconnectedness of economies, cultures, and people worldwide.",
            "Drivers include trade, technology, communication, and multinational corporations.",
            "Effects include cultural exchange, economic growth, but also inequality and environmental challenges."
        });
        
        knowledgeBase.put("human rights", new String[]{
            "Human rights are fundamental rights and freedoms that belong to every person, regardless of race, sex, nationality, or religion.",
            "Key documents: Universal Declaration of Human Rights (1948) outlines civil, political, economic, social, and cultural rights.",
            "Examples: right to life, freedom of speech, education, and protection from discrimination."
        });
        
        // Grade 10 English
        knowledgeBase.put("subject verb agreement", new String[]{
            "Subject-verb agreement means the subject and verb must match in number (singular/plural).",
            "Singular subjects take singular verbs: 'He walks', 'The team plays'.",
            "Plural subjects take plural verbs: 'They walk', 'The teams play'."
        });
        
        knowledgeBase.put("tenses", new String[]{
            "Tenses indicate when an action happens: past, present, future.",
            "Simple: I walked (past), I walk (present), I will walk (future).",
            "Perfect: I have walked (present perfect), I had walked (past perfect), I will have walked (future perfect)."
        });
        
        knowledgeBase.put("report writing", new String[]{
            "A report presents factual information in a structured format: title, introduction, body, conclusion.",
            "Use clear headings, bullet points, and concise language. Include data, sources, and recommendations if needed.",
            "Maintain an objective tone and organize information logically to guide the reader."
        });
        
        knowledgeBase.put("letter writing", new String[]{
            "Formal letters include sender's address, date, recipient's address, salutation, body, closing, and signature.",
            "Use a polite tone, clear purpose in the opening paragraph, and concise paragraphs in the body.",
            "Common types: complaint, inquiry, application, and thank-you letters."
        });
        
        knowledgeBase.put("prepositions", new String[]{
            "Prepositions show relationships in time or space: in, on, at, for, with, under, over, between.",
            "Time: at 5 PM, on Monday, in December, for an hour. Place: at the door, on the table, in the box.",
            "Common errors: ending sentences with unnecessary prepositions; confusing 'in' and 'at' for locations."
        });
        
        // Coding Basics (Python)
        knowledgeBase.put("python variables", new String[]{
            "A variable stores a value. In Python, you create it by assignment: name = 'Alice' or age = 15.",
            "Variable names must start with a letter or underscore, and can contain letters, digits, underscores.",
            "Python is dynamically typed: the same variable can hold different types at different times."
        });
        
        knowledgeBase.put("python loops", new String[]{
            "A loop repeats code. In Python, 'for' loops iterate over sequences: for i in range(5): print(i).",
            "'while' loops repeat while a condition is True: while x < 10: x += 1.",
            "Use 'break' to exit a loop early, 'continue' to skip to the next iteration."
        });
        
        knowledgeBase.put("python functions", new String[]{
            "A function groups reusable code. Define with 'def': def greet(name): return 'Hello ' + name.",
            "Functions can have parameters and return values. Call by name: greet('Alice').",
            "Use 'return' to send back a value; without it, the function returns None."
        });
        
        knowledgeBase.put("python lists", new String[]{
            "A list stores multiple items in order. Create with square brackets: fruits = ['apple', 'banana', 'cherry'].",
            "Access items by index (starts at 0): fruits[0] is 'apple'. Use negative index to count from the end: fruits[-1] is 'cherry'.",
            "Common methods: append() to add, remove() to delete, sort() to order, len() to get length."
        });
        
        knowledgeBase.put("python conditionals", new String[]{
            "Conditionals run code based on whether a condition is True or False using if, elif, else.",
            "Example: if age >= 18: print('adult'); elif age >= 13: print('teen'); else: print('child').",
            "Comparison operators: == (equal), != (not equal), <, >, <=, >=. Logical operators: and, or, not."
        });
        
        knowledgeBase.put("python dictionaries", new String[]{
            "A dictionary stores key-value pairs. Create with curly braces: student = {'name': 'Alice', 'age': 15}.",
            "Access values by key: student['name'] returns 'Alice'. Use get() to avoid KeyError if key missing.",
            "Common operations: keys(), values(), items(), len(), in to check key existence."
        });
    }
    
    /**
     * Initialize study tips
     */
    private void initializeStudyTips() {
        studyTips = new HashMap<>();
        
        studyTips.put("study tips", new String[]{
            "🎯 Tip 1: Create a dedicated study space free from distractions.",
            "📚 Tip 2: Use active recall - test yourself instead of just re-reading.",
            "⏰ Tip 3: Study in 25-30 minute blocks with short breaks (Pomodoro technique).",
            "📝 Tip 4: Take notes in your own words to enhance understanding.",
            "🤝 Tip 5: Teach concepts to someone else to identify gaps in knowledge.",
            "🗓️ Tip 6: Start preparing well in advance rather than cramming.",
            "💡 Tip 7: Connect new material with existing knowledge.",
            "😴 Tip 8: Get enough sleep - it's crucial for memory and focus."
        });
        
        studyTips.put("how to study effectively", new String[]{
            "1. Set clear, specific goals for each study session.",
            "2. Preview material before deep study (skim headings, summaries).",
            "3. Use multiple study methods: reading, writing, discussing, practicing.",
            "4. Review material regularly to reinforce learning.",
            "5. Practice problems similar to those on tests.",
            "6. Join study groups for collaboration and different perspectives.",
            "7. Manage stress through exercise, meditation, and proper nutrition."
        });
        
        studyTips.put("exam preparation", new String[]{
            "🏋️ Week before: Review all material systematically.",
            "📋 Days before: Practice with previous exams or sample questions.",
            "🌙 Night before: Get good sleep, not last-minute cramming!",
            "⏰ Day of: Arrive early, read all questions before answering.",
            "✨ During exam: Start with easy questions, manage your time wisely.",
            "🔍 Time management: Allocate time per question based on points/marks."
        });
        
        studyTips.put("memory techniques", new String[]{
            "🔗 Mnemonics: Create acronyms or phrases to remember lists.",
            "🎨 Visualization: Create mental images for concepts.",
            "🔄 Chunking: Break information into smaller, manageable pieces.",
            "🗂️ Organization: Group related information together.",
            "🔁 Spaced Repetition: Review material at increasing intervals.",
            "🎭 Association: Link new info with familiar concepts.",
            "📖 Storytelling: Create stories to connect facts together."
        });
    }
    
    /**
     * Initialize exam preparation resources
     */
    private void initializeExamPreparation() {
        examPreparation = new HashMap<>();
        
        examPreparation.put("exam preparation", new String[]{
            "📊 Know the exam format: multiple choice, essay, practical, or mixed.",
            "📚 Identify key topics from syllabus and focus on those.",
            "✍️ Practice writing answers in exam conditions (timed).",
            "📖 Review previous year question papers if available.",
            "👨‍🏫 Clarify doubts with your teacher or tutors.",
            "🤝 Form study groups with classmates.",
            "😌 Manage exam anxiety through breathing exercises and positive thinking."
        });
        
        examPreparation.put("test taking strategies", new String[]{
            "✓ Read instructions carefully and fully.",
            "✓ Scan all questions before starting.",
            "✓ Answer easier questions first to build confidence.",
            "✓ Manage time - don't spend too long on one question.",
            "✓ Review your answers if time permits.",
            "✓ Show your work in mathematical/scientific questions.",
            "✓ For multiple choice, eliminate obviously wrong answers first."
        });
        
        examPreparation.put("reduce exam anxiety", new String[]{
            "🧘 Practice mindfulness and deep breathing exercises.",
            "🏃 Exercise regularly for stress relief.",
            "😴 Maintain a healthy sleep schedule.",
            "🎯 Build confidence through thorough preparation.",
            "🤝 Talk to friends or counselors about your concerns.",
            "🧠 Use positive affirmations and visualization.",
            "⚖️ Balance studies with recreational activities."
        });
    }
    
    /**
     * Generate response based on user input
     * Prioritizes syllabus-relevant answers for Grade 1-10 students
     */
    public String getResponse(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Hello! I'm here to help with Grade 1-10 school subjects. Ask me any academic question about Mathematics, Science, English, Social Studies, or Computer Science! 📚";
        }
        
        String input = userInput.toLowerCase().trim();
        input = input.replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", " ").trim();
        
        // Check for greetings
        if (isGreeting(input)) {
            return getWelcomeMessage();
        }
        
        // Check for help request
        if (input.contains("help") || input.contains("what can you do") || input.contains("what can i ask")) {
            return getHelpMessage();
        }
        
        // Check for study tips
        if (input.contains("tip") || input.contains("how to study")) {
            return searchAndRespond(input, studyTips);
        }

        // Check for exam preparation
        if (input.contains("exam") || input.contains("test") || input.contains("preparation")) {
            return searchAndRespond(input, examPreparation);
        }

        // Check knowledge base
        String response = searchAndRespond(input, knowledgeBase);
        if (!response.equals("I don't have information about that topic yet.")) {
            return response;
        }

        // Check if question seems educational
        if (looksLikeEducationalQuestion(input)) {
            String topic = extractLikelyTopic(input);
            if (topic != null && !topic.isBlank()) {
                return "I don't have detailed information on '" + topic + "' yet. However, this seems to be a Grade 1-10 topic. "
                        + "Could you be more specific? Ask like:\n"
                        + "- 'Define [term]'\n"
                        + "- 'What is [topic]?'\n"
                        + "- 'How to solve [problem type]?'\n"
                        + "- 'Explain [concept] with example'";
            }
        }

        // Non-educational or out of scope
        return "I can answer only Grade 1-10 school curriculum questions. "
                + "Topics I cover: Mathematics, Science (Physics, Chemistry, Biology), English, Social Studies, and Computer Science. "
                + "What would you like to learn? 📖";
    }
    
    /**
     * Search the knowledge base and return a response
     */
    private String searchAndRespond(String normalizedInput, Map<String, String[]> database) {
        String direct = findDirectMatch(normalizedInput, database);
        if (direct != null) {
            return direct;
        }

        String best = findBestFuzzyMatch(normalizedInput, database);
        if (best != null) {
            return best;
        }

        if (looksLikeEducationalQuestion(normalizedInput)) {
            String topic = extractLikelyTopic(normalizedInput);
            if (topic != null && !topic.isBlank()) {
                return "I don't have a saved explanation for '" + topic + "' yet. " +
                        "Tell me your class/grade and what exactly you need (definition, example, or step-by-step), and I'll try to help.";
            }

            return "What topic is your question about? " +
                    "For example, ask: 'Define photosynthesis', 'Explain algebra with an example', or 'How do I solve a linear equation?'";
        }

        return "I don't have information about that topic yet.";
    }

    private String findDirectMatch(String normalizedInput, Map<String, String[]> database) {
        for (String key : database.keySet()) {
            String normalizedKey = key.toLowerCase().trim();
            if (normalizedInput.contains(normalizedKey)) {
                String[] responses = database.get(key);
                return responses[random.nextInt(responses.length)];
            }
        }
        return null;
    }

    private String findBestFuzzyMatch(String normalizedInput, Map<String, String[]> database) {
        Set<String> inputTokens = tokenizeMeaningful(normalizedInput);
        if (inputTokens.isEmpty()) {
            return null;
        }

        double bestScore = 0.0;
        String bestKey = null;

        for (String key : database.keySet()) {
            Set<String> keyTokens = tokenizeMeaningful(key.toLowerCase(Locale.ROOT));
            if (keyTokens.isEmpty()) {
                continue;
            }

            // Calculate Jaccard similarity
            double jaccardScore = jaccard(inputTokens, keyTokens);
            
            // Calculate partial word match score (bonus for containing key phrases)
            double partialScore = calculatePartialMatch(normalizedInput, key.toLowerCase());
            
            // Use higher score between Jaccard and partial match
            double score = Math.max(jaccardScore, partialScore);
            
            if (score > bestScore) {
                bestScore = score;
                bestKey = key;
            }
        }

        if (bestKey == null) {
            return null;
        }

        // Lowered threshold from 0.45 to 0.30 for better keyword flexibility
        if (bestScore >= 0.30) {
            String[] responses = database.get(bestKey);
            return responses[random.nextInt(responses.length)];
        }

        return null;
    }

    /**
     * Calculate partial word match score
     * Gives bonus when key terms appear even if phrased differently
     */
    private double calculatePartialMatch(String userInput, String keyPhrase) {
        String[] keyTokens = keyPhrase.split("\\s+");
        int matchedTokens = 0;
        
        for (String keyToken : keyTokens) {
            if (keyToken.length() > 2) {
                // Check if any word in user input contains this key token
                if (userInput.contains(keyToken)) {
                    matchedTokens++;
                }
            }
        }
        
        if (keyTokens.length == 0) return 0.0;
        return (double) matchedTokens / keyTokens.length;
    }

    private Set<String> tokenizeMeaningful(String text) {
        if (text == null) {
            return Collections.emptySet();
        }

        String normalized = text.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();

        if (normalized.isEmpty()) {
            return Collections.emptySet();
        }

        String[] parts = normalized.split(" ");
        Set<String> tokens = new HashSet<>();
        for (String p : parts) {
            if (p.isBlank() || p.length() <= 2) {
                continue;
            }
            if (STOP_WORDS.contains(p)) {
                continue;
            }
            tokens.add(p);
        }
        return tokens;
    }

    private double jaccard(Set<String> a, Set<String> b) {
        if (a.isEmpty() || b.isEmpty()) {
            return 0.0;
        }

        int intersection = 0;
        for (String t : a) {
            if (b.contains(t)) {
                intersection++;
            }
        }
        int union = a.size() + b.size() - intersection;
        return union == 0 ? 0.0 : ((double) intersection / (double) union);
    }

    private boolean looksLikeEducationalQuestion(String normalizedInput) {
        if (normalizedInput == null) {
            return false;
        }
        return normalizedInput.contains("what is") || normalizedInput.contains("define") || normalizedInput.contains("explain") ||
                normalizedInput.contains("how to") || normalizedInput.startsWith("why ") || normalizedInput.startsWith("how ") ||
                normalizedInput.startsWith("what ") || normalizedInput.contains("difference between") || normalizedInput.contains("solve");
    }

    private String extractLikelyTopic(String normalizedInput) {
        if (normalizedInput == null) {
            return null;
        }

        String s = normalizedInput;
        String[] prefixes = {
                "what is ", "define ", "definition of ", "explain ", "meaning of ", "tell me about ", "how to ", "why ", "what ", "how "
        };

        for (String p : prefixes) {
            if (s.startsWith(p)) {
                s = s.substring(p.length());
                break;
            }
        }

        s = s.replaceAll("\\b(a|an|the)\\b", " ").replaceAll("\\s+", " ").trim();
        if (s.length() > 80) {
            s = s.substring(0, 80).trim();
        }
        return s.isBlank() ? null : s;
    }
    
    /**
     * Check if input is a greeting
     */
    private boolean isGreeting(String input) {
        String[] greetings = {"hello", "hi", "hey", "greetings", "hola", "namaste", "good morning", "good afternoon", "good evening"};
        for (String greeting : greetings) {
            if (input.contains(greeting)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Welcome message
     */
    private String getWelcomeMessage() {
        String[] greetings = {
            "Hello! 👋 I'm an AI Educational Chatbot powered by artificial intelligence. I can help you with Grade 1-10 curriculum. What would you like to learn?",
            "Hi there! 🤖 I'm an AI assistant ready to help you explore Mathematics, Science, English, Social Studies, or Computer Science. Ask me anything!",
            "Welcome! 🎓 I'm an AI chatbot here to explain school topics in clear, easy-to-understand language. What's your question?"
        };
        return greetings[random.nextInt(greetings.length)];
    }
    
    /**
     * Help message listing all subjects and topics
     */
    private String getHelpMessage() {
        return """
               📚 **I can teach you Grade 1-10 School Subjects:**

               🔢 **MATHEMATICS**: Number systems, fractions, algebra, geometry, trigonometry, statistics, formulas

               🔬 **SCIENCE**:
                  🧪 Physics: Motion, force, work, power, light, electricity, magnetism
                  🌱 Biology: Cells, photosynthesis, digestion, reproduction, ecosystems
                  ⚗️ Chemistry: Matter, reactions, acids, bases, elements, compounds

               📖 **ENGLISH**: Grammar, tenses, parts of speech, writing, comprehension, vocabulary

               🌍 **SOCIAL STUDIES**:
                  📜 History, 🗺️ Geography, 🏛️ Civics, 💰 Economics

               💻 **COMPUTER SCIENCE**: Hardware, software, internet, programming basics, cyber safety

               **Ask me like:**
               - 'What is algebra?'
               - 'Define photosynthesis'
               - 'How to solve linear equations?'
               - 'Explain the water cycle'
               - 'Study tips for exams'

               Let's learn together! 🌟
               """;
    }
    
    /**
     * Get a motivational message
     */
    public String getMotivationalMessage() {
        String[] messages = {
            "✨ Every expert was once a beginner. Keep learning and growing!",
            "🌟 Your hard work today will be your success tomorrow. Keep it up!",
            "💪 Mistakes are proof you're trying. Learn from them and improve!",
            "🎯 Focus on progress, not perfection. You're doing great!",
            "🔥 Believe in yourself! You can learn anything with dedication.",
            "🌈 Learning is an adventure. Enjoy the journey, not just the destination!",
            "📚 Knowledge is power. Every question you ask makes you smarter!",
            "🚀 You've got this! Stay curious and never stop learning."
        };
        return messages[random.nextInt(messages.length)];
    }
}
