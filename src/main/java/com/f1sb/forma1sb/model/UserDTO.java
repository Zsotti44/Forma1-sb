package com.f1sb.forma1sb.model;

/**
 * UserDTO, felhasznalo adatbázis tábla
 */
public class UserDTO {

    private final int id;
    private String name;
    private String password;
    private final byte permission;
    private String salt;

    public UserDTO(int id, String name, String password, byte permission, String salt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permission = permission;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getPermission() {
        return permission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
