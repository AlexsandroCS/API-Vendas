package io.github.AlexsandroCS.Vendas.domain.ENUM;

public enum UserRoles {

    ADMIN_ROLE("admin"),
    USER_ROLE("user");

    private String role;

    UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
