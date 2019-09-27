package analizadorLexico;

import java.util.Hashtable;

public class Token {

    private int id;
    private String lexeme, description, type = "";
    private Hashtable<String, Object> atributos = new Hashtable<String, Object>();

    public Token(int id, String lexeme, String description) {
        this.id = id;
        this.lexeme = lexeme;
        this.description = description;
        this.type = "sin definir";
    }

    public void addAttribute(String caract, Object val) {
        atributos.put(caract, val);
    }

    public String getAttribute(String caract) {
        return (String) atributos.get(caract);
    }

    public int getID() {
        return id;
    }

    public void setLexeme(String lexema) {
        this.lexeme = lexema;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
 

    public String toString() {
        return "Token: " + id + " | Lexema: " + lexeme + " | " + "Descripción: " + description + " | Tipo: " + type;
        /*
        if (description == null) {
            return lexeme;
        }
        return description + " " + lexeme;
         */
    }
}
