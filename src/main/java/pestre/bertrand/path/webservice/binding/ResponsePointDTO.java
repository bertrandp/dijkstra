package pestre.bertrand.path.webservice.binding;

public class ResponsePointDTO {

    private int id;
    private String name;
    private int distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public static final class ResponseDTOBuilder {
        private int id;
        private String name;
        private int distance;

        private ResponseDTOBuilder() {
        }

        public static ResponseDTOBuilder aResponseDTO() {
            return new ResponseDTOBuilder();
        }

        public ResponseDTOBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ResponseDTOBuilder distance(int distance) {
            this.distance = distance;
            return this;
        }

        public ResponsePointDTO build() {
            ResponsePointDTO responseDTO = new ResponsePointDTO();
            responseDTO.setId(id);
            responseDTO.setName(name);
            responseDTO.setDistance(distance);
            return responseDTO;
        }
    }
}
