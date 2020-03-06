package builder;

public class BuildingSearchBuilder {
    private String name;
    private String district;
    private Integer buildingArea;
    private Integer numberOfBasement;
    private Integer areaRentFrom;
    private Integer areaRentTo;

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getBuildingArea() {
        return buildingArea;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public Integer getAreaRentFrom() {
        return areaRentFrom;
    }

    public Integer getAreaRentTo() {
        return areaRentTo;
    }

    public BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
        this.buildingArea = builder.buildingArea;
        this.numberOfBasement = builder.numberOfBasement;
        this.areaRentFrom = builder.areaRentFrom;
        this.areaRentTo = builder.areaRentTo;
    }

    public static class Builder {
        private String name;
        private String district;
        private Integer buildingArea;
        private Integer numberOfBasement;
        private Integer areaRentFrom;
        private Integer areaRentTo;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setBuildingArea(Integer buildingArea) {
            this.buildingArea = buildingArea;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setAreaRentFrom(Integer areaRentFrom) {
            this.areaRentFrom = areaRentFrom;
            return this;
        }

        public Builder setAreaRentTo(Integer areaRentTo) {
            this.areaRentTo = areaRentTo;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
