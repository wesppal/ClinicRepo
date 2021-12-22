package by.overone.clinic.dto;

import by.overone.clinic.model.Pet;
import by.overone.clinic.util.constant.PetConst;
import lombok.Data;

@Data
public class PetParamDTO {
    private String name;
    private int age;
    private String type;
    private String owner;
    private long userId;
    private StringBuilder param = new StringBuilder();

    public StringBuilder getParam() {
        param.append(" ");
        if (name != null) {
            param.append(PetConst.NAME + " = (?),");
        }
        if (age != 0) {
             param.append(PetConst.AGE + " = (?),");
        }
        if (type != null) {
            param.append(PetConst.TYPE + " = (?),");
        }
        if (owner != null) {
            param.append(PetConst.OWNER + " = (?),");
        }
        if (userId != 0) {
            param.append(PetConst.USER_ID + " = (?),");
        }
        param.deleteCharAt(param.length()-1);
        param.append(" WHERE pet_id = (?)");
        return param;
    }
    public PetParamDTO copyParam(PetParamDTO petParamDTO, Pet pet){
        petParamDTO.setName(pet.getName());
        petParamDTO.setAge(pet.getAge());
        petParamDTO.setType(pet.getType());
        petParamDTO.setOwner(pet.getOwner());
        petParamDTO.setUserId(pet.getUser_id());
        return petParamDTO;
    }
}
