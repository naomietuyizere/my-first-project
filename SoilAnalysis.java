// SoilAnalysis class
public class SoilAnalysis {
    // Attributes
    private int farmerId;
    private String districtName;
    private double nitrogenLevel;
    private double phosphorusLevel;
    private double potassiumLevel;
    private String cropType;

    // Constructor
    public SoilAnalysis(int farmerId, String districtName, double nitrogenLevel,
                        double phosphorusLevel, double potassiumLevel, String cropType) {
        this.farmerId = farmerId;
        this.districtName = districtName;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphorusLevel = phosphorusLevel;
        this.potassiumLevel = potassiumLevel;
        this.cropType = cropType;
    }

    // Getters
    public int getFarmerId() {
        return farmerId;
    }

    public String getFormattedFarmerId() {
        return String.format("F%03d", farmerId);
    }

    public String getDistrictName() {
        return districtName;
    }

    public double getNitrogenLevel() {
        return nitrogenLevel;
    }

    public double getPhosphorusLevel() {
        return phosphorusLevel;
    }

    public double getPotassiumLevel() {
        return potassiumLevel;
    }

    public String getCropType() {
        return cropType;
    }

    // Methods
    public boolean isBalanced() {
        return nitrogenLevel >= 20 && nitrogenLevel <= 100 &&
                phosphorusLevel >= 20 && phosphorusLevel <= 100 &&
                potassiumLevel >= 20 && potassiumLevel <= 100;
    }

    public String calculateFertilizerNeeded() {
        // Check for invalid nutrient readings
        if (nitrogenLevel <= 0 || phosphorusLevel <= 0 || potassiumLevel <= 0) {
            throw new IllegalArgumentException("Invalid nutrient reading");
        }

        if (isBalanced()) {
            return "OPTIMAL - Maintenance fertilizer only";
        }

        // Check for deficiencies and excesses
        StringBuilder deficientNutrients = new StringBuilder();
        StringBuilder excessNutrients = new StringBuilder();

        if (nitrogenLevel < 20) deficientNutrients.append("Nitrogen,");
        if (phosphorusLevel < 20) deficientNutrients.append("Phosphorus,");
        if (potassiumLevel < 20) deficientNutrients.append("Potassium,");

        if (nitrogenLevel > 100) excessNutrients.append("Nitrogen,");
        if (phosphorusLevel > 100) excessNutrients.append("Phosphorus,");
        if (potassiumLevel > 100) excessNutrients.append("Potassium,");

        // Remove trailing commas and build message
        String deficientStr = deficientNutrients.length() > 0 ?
                deficientNutrients.substring(0, deficientNutrients.length() - 1) : "";
        String excessStr = excessNutrients.length() > 0 ?
                excessNutrients.substring(0, excessNutrients.length() - 1) : "";

        // Build recommendation message
        if (deficientStr.length() > 0 && excessStr.length() > 0) {
            return "DEFICIENT - High application needed for [" + deficientStr + "] | EXCESS - Reduce [" + excessStr + "] application";
        } else if (deficientStr.length() > 0) {
            return "DEFICIENT - High application needed for [" + deficientStr + "]";
        } else {
            return "EXCESS - Reduce [" + excessStr + "] application";
        }
    }
}

// FertilizerAdvisorySystem class with registration number
class FertilizerAdvisorySystem_24RP00900 {

    public static void main(String[] args) {
        System.out.println("Fertilizer Advisory System Report");
        System.out.println("==================================\n");

        // Create five SoilAnalysis objects with numerical farmer IDs and Rwandan districts
        SoilAnalysis sample1 = new SoilAnalysis(1, "Gasabo", 45.5, 60.2, 75.8, "Maize");
        SoilAnalysis sample2 = new SoilAnalysis(2, "Nyarugenge", 15.0, 25.5, 80.0, "Beans");
        SoilAnalysis sample3 = new SoilAnalysis(3, "Kicukiro", 85.0, 120.5, 65.0, "Rice");
        SoilAnalysis sample4 = new SoilAnalysis(4, "Rubavu", 15.5, 110.0, 18.5, "Potatoes");
        SoilAnalysis sample5 = new SoilAnalysis(5, "Musanze", 35.0, 12.5, 8.0, "Wheat");

        // Test all samples
        SoilAnalysis[] samples = {sample1, sample2, sample3, sample4, sample5};

        for (int i = 0; i < samples.length; i++) {
            SoilAnalysis sample = samples[i];
            System.out.println("Farmer ID: " + sample.getFormattedFarmerId());
            System.out.println("District: " + sample.getDistrictName());
            System.out.println("Crop Type: " + sample.getCropType());

            try {
                String recommendation = sample.calculateFertilizerNeeded();
                System.out.println("Recommendation: " + recommendation);
            } catch (IllegalArgumentException e) {
                System.out.println("Recommendation: ERROR - " + e.getMessage());
            }
            System.out.println();
        }
    }
}