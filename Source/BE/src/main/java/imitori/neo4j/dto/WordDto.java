package imitori.neo4j.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("word")
    public String word;

    @JsonProperty("spell")
    public ArrayList<String> spell;

    @JsonProperty("lang")
    public String lang;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WordDto(Integer id, String word, ArrayList<String> spell, String lang) {
        this.id = id;
        this.word = word;
        this.spell = spell;
        this.lang = lang;
    }

    @Override
    public String toString() {
        String spelllist = new String();
        if (this.spell.size() < 2) {
            spelllist = "[" + this.spell.get(0) + "]";
        } else {
            spelllist = "[" + this.spell.get(0);
            for (int i = 0; i < this.spell.size(); i++) {
                spelllist += ", " + this.spell.get(i);
            }
            spelllist += "]";
        }
        return "WordDto{id: " + id + ", word: " + word + ", spell : " + spelllist + ", lang: " + lang + "}";
    }
}