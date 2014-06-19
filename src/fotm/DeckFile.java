package fotm;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class DeckFile {
    public static Deck loadFrom(String path) {
        try {
            File f = new File(path);
            Document doc = Jsoup.parse(f, null);

            Deck deck = createEmptyDeck(doc);
            
            if(deck.getType() == DeckType.Hero) {
                List<Card> cards = parseHeroDeckCards(doc);
                deck.getCards().addAll(cards);
            }
            
            return deck;
        }
        catch(IOException ex) {
            return null;
        }
    }
    
    private static Deck createEmptyDeck(Document doc) {
        switch (getDeckType(doc).toLowerCase()) {
            case "herodeck":
                return new Deck(DeckType.Hero);
            case "villaindeck":
                return new Deck(DeckType.Villain);
            case "environmentdeck":
                return new Deck(DeckType.Environment);
        }
        
        return null;
    }
    
    private static String getDeckType(Document d) {
        Elements els = d.getElementsByTag("type");
        return els.isEmpty() ? "deck" : els.first().text();
    }
    
    private static List<Card> parseHeroDeckCards(Document doc) {
        List<Card> cards = new ArrayList<>();
        
        cards.addAll(parseHeroFrontCards(doc));
        cards.addAll(parseHeroBackCards(doc));
        cards.addAll(parseHeroCards(doc));
        
        return cards;
    }
    
    private static List<HeroCard> parseHeroCards(Document doc) {
        List<HeroCard> cards = new ArrayList<>();
        
        Elements els = doc.getElementsByTag("herocard");
        for (Element el : els) {
            Integer id = new Integer(findElement(el, "id"));
            String name = findElement(el, "name");
            HeroCard card = new HeroCard(id, name);
            card.setClasses(findElement(el, "classes"));
            card.setHp(findElement(el, "healthpoints"));
            card.setHpImagePath(findElement(el, "healthpointsimage"));
            card.setHpVisible(Boolean.valueOf(findElement(el, "healthpointsvisible")));
            card.setPortraitPath(findElement(el, "portrait"));
            card.setIndex(new Integer(findElement(el, "numberindeck")));
            card.setQuote(findElement(el, "quotestring1"));
            card.setQuote2(findElement(el, "quotestring2"));
            card.setIssue(findElement(el, "issuestring"));
            //card.setNameColor(new Color(new Integer(findElement(el, "namecolour"))));
            //card.setClassColor(new Color(new Integer(findElement(el, "classcolour"))));
            card.setText(findElement(el, "cardtext"));

            /*if (findElement(el, "namefontcolor").isEmpty() == false) {
                card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor"))));
            }

            if (findElement(el, "hpfontcolor").isEmpty() == false) {
                card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor"))));
            }

            if (findElement(el, "classfontcolor").isEmpty() == false) {
                card.setClassFontColor(new Color(new Integer(findElement(el, "classfontcolor"))));
            }

            if (findElement(el, "descriptionfontcolor").isEmpty() == false) {
                card.setDescriptionFontColor(new Color(new Integer(findElement(el, "descriptionfontcolor"))));
            }

            if (findElement(el, "quotefontcolor").isEmpty() == false) {
                card.setQuoteFontColor(new Color(new Integer(findElement(el, "quotefontcolor"))));
            }

            if (findFontElement(el, "namefont") != null) {
                card.setNameFont(findFontElement(el, "namefont"));
            }

            if (findFontElement(el, "classfont") != null) {
                card.setClassFont(findFontElement(el, "classfont"));
            }

            if (findFontElement(el, "hpfont") != null) {
                card.setHpFont(findFontElement(el, "hpfont"));
            }

            if (findFontElement(el, "descriptionfont") != null) {
                card.setDescriptionFont(findFontElement(el, "descriptionfont"));
            }

            if (findFontElement(el, "quotefont") != null) {
                card.setQuoteFont(findFontElement(el, "quotefont"));
            }*/
            
            //card.setIsDirty(false);

            cards.add(card);
        }
        
        return cards;
    }
    
    private static List<Card> parseHeroFrontCards(Document doc) {
        List<Card> cards = new ArrayList<>();
        
        Elements els = doc.getElementsByTag("herofrontcard");
        for (Element el : els) {
            Integer id = new Integer(findElement(el, "id"));
            String name = findElement(el, "name");
            HeroFrontCard card = new HeroFrontCard(id, name);
            card.setClasses(findElement(el, "classes"));
            card.setHp(findElement(el, "healthpoints"));
            card.setPortraitPath(findElement(el, "portrait"));
            card.setPowerName(findElement(el, "powername"));
            card.setPowerText(findElement(el, "powertext"));
            card.setNemesisImagePath(findElement(el, "nemesispath"));
            card.setIndex(new Integer(findElement(el, "numberindeck")));
            //card.setColor(new Color(Integer.parseInt(findElement(el, "powercolor"))));

            /*if (findElement(el, "namefontcolor").isEmpty() == false) {
                card.setNameFontColor(new Color(new Integer(findElement(el, "namefontcolor"))));
            }

            if (findElement(el, "hpfontcolor").isEmpty() == false) {
                card.setHpFontColor(new Color(new Integer(findElement(el, "hpfontcolor"))));
            }

            if (findElement(el, "powernamefontcolor").isEmpty() == false) {
                card.setPowerNameFontColor(new Color(new Integer(findElement(el, "powernamefontcolor"))));
            }

            if (findElement(el, "powerfontcolor").isEmpty() == false) {
                card.setPowerFontColor(new Color(new Integer(findElement(el, "powerfontcolor"))));
            }

            if (findFontElement(el, "namefont") != null) {
                card.setNameFont(findFontElement(el, "namefont"));
            }

            if (findFontElement(el, "powernamefont") != null) {
                card.setPowerNameFont(findFontElement(el, "powernamefont"));
            }

            if (findFontElement(el, "hpfont") != null) {
                card.setHpFont(findFontElement(el, "hpfont"));
            }

            if (findFontElement(el, "powerfont") != null) {
                card.setPowerFont(findFontElement(el, "powerfont"));
            }

            card.setIsDirty(false);*/
            
            cards.add(card);
        }
        
        return cards;
    }
    
    private static List<Card> parseHeroBackCards(Document doc) {
        List<Card> cards = new ArrayList<>();
        
        Elements els = doc.getElementsByTag("herobackcard");
        for (Element el : els) {
            Integer id = new Integer(findElement(el, "id"));
            String name = findElement(el, "name");
            HeroBackCard card = new HeroBackCard(id, name);
            card.setClasses(findElement(el, "classes"));
            //card.setHp(findElement(el, "healthpoints"));
            card.setPortraitPath(findElement(el, "portrait"));
            card.setIndex(new Integer(findElement(el, "numberindeck")));
            //card.setTextColour(new Color(new Integer(findElement(el, "abilitycolor"))));
            card.setAbilityLine1(findElement(el, "abilityline1"));
            card.setAbilityLine2(findElement(el, "abilityline2"));
            card.setAbilityLine3(findElement(el, "abilityline3"));
            card.setAbilityLine4(findElement(el, "abilityline4"));
            card.setAbilityLine5(findElement(el, "abilityline5"));
            card.setAbilityLine6(findElement(el, "abilityline6"));

            /*if (findElement(el, "textfontcolor").isEmpty() == false) {
                card.setTextFontColor(new Color(new Integer(findElement(el, "textfontcolor"))));
            }

            if (findFontElement(el, "textfont") != null) {
                card.setTextFont(findFontElement(el, "textfont"));
            }
            
            card.setIsDirty(false);*/

            cards.add(card);
        }
        
        return cards;
    }
    
    
    private static String findElement(Element el, String attr) {
        String val = "";
        Elements subels = el.getElementsByTag(attr);
        for (Element sel : subels) {
            List<TextNode> nodes = sel.textNodes();
            if(nodes == null || nodes.isEmpty()) {
                val = sel.text();
            } 
            else {
                for(TextNode node : nodes) {
                    val += node.getWholeText();
                }
            }
        }
        
        return val;
    }
}
