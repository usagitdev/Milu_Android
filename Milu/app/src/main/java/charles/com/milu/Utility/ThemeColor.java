package charles.com.milu.Utility;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by PIG18 on 1/25/2017.
 */

public class ThemeColor {

    private String firstcolor;
    private String endcolor;
    private String name;

    private int r;
    private int g;
    private int b;

    public ThemeColor (String name, String firstcolor, String endcolor){
        this.firstcolor = firstcolor;
        this.endcolor = endcolor;
        this.name = name;
        int color = Color.parseColor(firstcolor);
        r = (color >> 16) & 0xFF;
        g = (color >> 8) & 0xFF;
        b = (color >> 0) & 0xFF;
    }

    public String getFirstcolor() {
        return firstcolor;
    }

    public String getEndcolor() {
        return endcolor;
    }

    public String getName() {
        return name;
    }

    public static ThemeColor[] themeColors()
    {

        ArrayList<ThemeColor> themes = new ArrayList<>();
        themes.add(new ThemeColor("Midnight City","#232526","#414345"));
        themes.add(new ThemeColor("Noon to Dusk","#ff6e7f","#bfe9ff"));
        themes.add(new ThemeColor("YouTube","#e52d27","#b31217"));
        themes.add(new ThemeColor("CoolBrown","#603813","#b29f94"));
        themes.add(new ThemeColor("Harmonic Energy","#16A085","#F4D03F"));
        themes.add(new ThemeColor("Playing with Reds","#D31027","#EA384D"));
        themes.add(new ThemeColor("Sunny Days","#EDE574","#E1F5C4"));
        themes.add(new ThemeColor("Green Beach","#02AAB0","#00CDAC"));
        themes.add(new ThemeColor("Intuitive Purple","#DA22FF","#9733EE"));
        themes.add(new ThemeColor("Emerald Water","#348F50","#56B4D3"));
        themes.add(new ThemeColor("Lemon Twist","#3CA55C","#B5AC49"));
        themes.add(new ThemeColor("Horizon","#003973","#E5E5BE"));
        themes.add(new ThemeColor("Rose Water","#E55D87","#5FC3E4"));
        themes.add(new ThemeColor("Frozen","#403B4A","#E7E9BB"));
        themes.add(new ThemeColor("Mango Pulp","#F09819","#EDDE5D"));
        themes.add(new ThemeColor("Bloody Mary","#FF512F","#DD2476"));
        themes.add(new ThemeColor("Aubergine","#AA076B","#61045F"));
        themes.add(new ThemeColor("Aqua Marine","#1A2980","#26D0CE"));
        themes.add(new ThemeColor("Sunrise","#FF512F","#F09819"));
        themes.add(new ThemeColor("Purple Paradise","#1D2B64","#F8CDDA"));
        themes.add(new ThemeColor("Sea Weed","#4CB8C4","#3CD3AD"));
        themes.add(new ThemeColor("Pinky","#DD5E89","#F7BB97"));
        themes.add(new ThemeColor("Cherry","#EB3349","#F45C43"));
        themes.add(new ThemeColor("Mojito","#1D976C","#93F9B9"));
        themes.add(new ThemeColor("Juicy Orange","#FF8008","#FFC837"));
        themes.add(new ThemeColor("Mirage","#16222A","#3A6073"));
        themes.add(new ThemeColor("Steel Gray","#1F1C2C","#928DAB"));
        themes.add(new ThemeColor("Kashmir","#614385","#516395"));
        themes.add(new ThemeColor("Electric Violet","#4776E6","#8E54E9"));
        themes.add(new ThemeColor("Venice Blue","#085078","#85D8CE"));
        themes.add(new ThemeColor("Bora Bora","#2BC0E4","#EAECC6"));
        themes.add(new ThemeColor("Moss","#134E5E","#71B280"));
        themes.add(new ThemeColor("Shroom Haze","#5C258D","#4389A2"));
        themes.add(new ThemeColor("Mystic","#757F9A","#D7DDE8"));
        themes.add(new ThemeColor("Sea Blizz","#1CD8D2","#93EDC7"));
        themes.add(new ThemeColor("Opa","#3D7EAA","#FFE47A"));
        themes.add(new ThemeColor("Titanium","#283048","#859398"));
        themes.add(new ThemeColor("Mantle","#24C6DC","#514A9D"));
        themes.add(new ThemeColor("Dracula","#DC2424","#4A569D"));
        themes.add(new ThemeColor("Peach","#ED4264","#FFEDBC"));
        themes.add(new ThemeColor("Moonrise","#DAE2F8","#D6A4A4"));
        themes.add(new ThemeColor("Clouds","#ECE9E6","#FFFFFF"));
        themes.add(new ThemeColor("Stellar","#7474BF","#348AC7"));
        themes.add(new ThemeColor("Bourbon","#EC6F66","#F3A183"));
        themes.add(new ThemeColor("Calm Darya","#5f2c82","#49a09d"));
        themes.add(new ThemeColor("Influenza","#C04848","#480048"));
        themes.add(new ThemeColor("Shrimpy","#e43a15","#e65245"));
        themes.add(new ThemeColor("Army","#414d0b","#727a17"));
        themes.add(new ThemeColor("Miaka","#FC354C","#0ABFBC"));
        themes.add(new ThemeColor("Pinot Noir","#4b6cb7","#182848"));
        themes.add(new ThemeColor("Day Tripper","#f857a6","#ff5858"));
        themes.add(new ThemeColor("Namn","#a73737","#7a2828"));
        themes.add(new ThemeColor("Blurry Beach","#d53369","#cbad6d"));
        themes.add(new ThemeColor("Vasily","#e9d362","#333333"));
        themes.add(new ThemeColor("A Lost Memory","#DE6262","#FFB88C"));
        themes.add(new ThemeColor("Petrichor","#666600","#999966"));
        themes.add(new ThemeColor("Jonquil","#FFEEEE","#DDEFBB"));
        themes.add(new ThemeColor("Sirius Tamed","#EFEFBB","#D4D3DD"));
        themes.add(new ThemeColor("Kyoto","#c21500","#ffc500"));
        themes.add(new ThemeColor("Misty Meadow","#215f00","#e4e4d9"));
        themes.add(new ThemeColor("Aqualicious","#50C9C3","#96DEDA"));
        themes.add(new ThemeColor("Moor","#616161","#9bc5c3"));
        themes.add(new ThemeColor("Almost","#ddd6f3","#faaca8"));
        themes.add(new ThemeColor("Forever Lost","#5D4157","#A8CABA"));
        themes.add(new ThemeColor("Winter","#E6DADA","#274046"));
        themes.add(new ThemeColor("Autumn","#DAD299","#B0DAB9"));
        themes.add(new ThemeColor("Candy","#D3959B","#BFE6BA"));
        themes.add(new ThemeColor("Reef","#00d2ff","#3a7bd5"));
        themes.add(new ThemeColor("The Strain","#870000","#190A05"));
        themes.add(new ThemeColor("Dirty Fog","#B993D6","#8CA6DB"));
        themes.add(new ThemeColor("Earthly","#649173","#DBD5A4"));
        themes.add(new ThemeColor("Virgin","#C9FFBF","#FFAFBD"));
        themes.add(new ThemeColor("Ash","#606c88","#3f4c6b"));
        themes.add(new ThemeColor("Shadow Night","#000000","#53346D"));
        themes.add(new ThemeColor("Cherryblossoms","#FBD3E9","#BB377D"));
        themes.add(new ThemeColor("Parklife","#ADD100","#7B920A"));
        themes.add(new ThemeColor("Dance To Forget","#FF4E50","#F9D423"));
        themes.add(new ThemeColor("Starfall","#F0C27B","#4B1248"));
        themes.add(new ThemeColor("Red Mist","#000000","#e74c3c"));
        themes.add(new ThemeColor("Teal Love","#AAFFA9","#11FFBD"));
        themes.add(new ThemeColor("Neon Life","#B3FFAB","#12FFF7"));
        themes.add(new ThemeColor("Man of Steel","#780206","#061161"));
        themes.add(new ThemeColor("Amethyst","#9D50BB","#6E48AA"));
        themes.add(new ThemeColor("Cheer Up Emo Kid","#556270","#FF6B6B"));
        themes.add(new ThemeColor("Shore","#70e1f5","#ffd194"));
        themes.add(new ThemeColor("Facebook Messenger","#00c6ff","#0072ff"));
        themes.add(new ThemeColor("SoundCloud","#fe8c00","#f83600"));
        themes.add(new ThemeColor("Behongo","#52c234","#061700"));
        themes.add(new ThemeColor("ServQuick","#485563","#29323c"));
        themes.add(new ThemeColor("Friday","#83a4d4","#b6fbff"));
        themes.add(new ThemeColor("Martini","#FDFC47","#24FE41"));
        themes.add(new ThemeColor("Metallic Toad","#abbaab","#ffffff"));
        themes.add(new ThemeColor("Between The Clouds","#73C8A9","#373B44"));
        themes.add(new ThemeColor("Crazy Orange I","#D38312","#A83279"));
        themes.add(new ThemeColor("Hersheys","#1e130c","#9a8478"));
        themes.add(new ThemeColor("Talking To Mice Elf","#948E99","#2E1437"));
        themes.add(new ThemeColor("Purple Bliss","#360033","#0b8793"));
        themes.add(new ThemeColor("Predawn","#FFA17F","#00223E"));
        themes.add(new ThemeColor("Endless River","#43cea2","#185a9d"));
        themes.add(new ThemeColor("Pastel Orange at the Sun","#ffb347","#ffcc33"));
        themes.add(new ThemeColor("Twitch","#6441A5","#2a0845"));
        themes.add(new ThemeColor("Instagram","#517fa4","#243949"));
        themes.add(new ThemeColor("Flickr","#ff0084","#33001b"));
        themes.add(new ThemeColor("Vine","#00bf8f","#001510"));
        themes.add(new ThemeColor("Turquoise flow","#136a8a","#267871"));
        themes.add(new ThemeColor("Portrait","#8e9eab","#eef2f3"));
        themes.add(new ThemeColor("Virgin America","#7b4397","#dc2430"));
        themes.add(new ThemeColor("Koko Caramel","#D1913C","#FFD194"));
        themes.add(new ThemeColor("Fresh Turboscent","#F1F2B5","#135058"));
        themes.add(new ThemeColor("Green to dark","#6A9113","#141517"));
        themes.add(new ThemeColor("Ukraine","#004FF9","#FFF94C"));
        themes.add(new ThemeColor("Curiosity blue","#525252","#3d72b4"));
        themes.add(new ThemeColor("Dark Knight","#BA8B02","#181818"));
        themes.add(new ThemeColor("Piglet","#ee9ca7","#ffdde1"));
        themes.add(new ThemeColor("Lizard","#304352","#d7d2cc"));
        themes.add(new ThemeColor("Sage Persuasion","#CCCCB2","#757519"));
        themes.add(new ThemeColor("Between Night and Day","#2c3e50","#3498db"));
        themes.add(new ThemeColor("Timber","#fc00ff","#00dbde"));
        themes.add(new ThemeColor("Passion","#e53935","#e35d5b"));
        themes.add(new ThemeColor("Clear Sky","#005C97","#363795"));
        themes.add(new ThemeColor("Master Card","#f46b45","#eea849"));
        themes.add(new ThemeColor("Back To Earth","#00C9FF","#92FE9D"));
        themes.add(new ThemeColor("Deep Purple","#673AB7","#512DA8"));
        themes.add(new ThemeColor("Little Leaf","#76b852","#8DC26F"));
        themes.add(new ThemeColor("Netflix","#8E0E00","#1F1C18"));
        themes.add(new ThemeColor("Light Orange","#FFB75E","#ED8F03"));
        themes.add(new ThemeColor("Green and Blue","#c2e59c","#64b3f4"));
        themes.add(new ThemeColor("Poncho","#403A3E","#BE5869"));
        themes.add(new ThemeColor("Back to the Future","#C02425","#F0CB35"));
        themes.add(new ThemeColor("Blush","#B24592","#F15F79"));
        themes.add(new ThemeColor("Inbox","#457fca","#5691c8"));
        themes.add(new ThemeColor("Purplin","#6a3093","#a044ff"));
        themes.add(new ThemeColor("Pale Wood","#eacda3","#d6ae7b"));
        themes.add(new ThemeColor("Haikus","#fd746c","#ff9068"));
        themes.add(new ThemeColor("Pizelex","#114357","#F29492"));
        themes.add(new ThemeColor("Joomla","#1e3c72","#2a5298"));
        themes.add(new ThemeColor("Christmas","#2F7336","#AA3A38"));
        themes.add(new ThemeColor("Minnesota Vikings","#5614B0","#DBD65C"));
        themes.add(new ThemeColor("Miami Dolphins","#4DA0B0","#D39D38"));
        themes.add(new ThemeColor("Forest","#5A3F37","#2C7744"));
        themes.add(new ThemeColor("Nighthawk","#2980b9","#2c3e50"));
        themes.add(new ThemeColor("Superman","#0099F7","#F11712"));
        themes.add(new ThemeColor("Suzy","#834d9b","#d04ed6"));
        themes.add(new ThemeColor("Dark Skies","#4B79A1","#283E51"));
        themes.add(new ThemeColor("Deep Space","#000000","#434343"));
        themes.add(new ThemeColor("Decent","#4CA1AF","#C4E0E5"));
        themes.add(new ThemeColor("Colors Of Sky","#E0EAFC","#CFDEF3"));
        themes.add(new ThemeColor("Purple White","#BA5370","#F4E2D8"));
        themes.add(new ThemeColor("Ali","#ff4b1f","#1fddff"));
        themes.add(new ThemeColor("Alihossein","#f7ff00","#db36a4"));
        themes.add(new ThemeColor("Shahabi","#a80077","#66ff00"));
        themes.add(new ThemeColor("Red Ocean","#1D4350","#A43931"));
        themes.add(new ThemeColor("Tranquil","#EECDA3","#EF629F"));
        themes.add(new ThemeColor("Transfile","#16BFFD","#CB3066"));
        themes.add(new ThemeColor("Sylvia","#ff4b1f","#ff9068"));
        themes.add(new ThemeColor("Sweet Morning","#FF5F6D","#FFC371"));
        themes.add(new ThemeColor("Politics","#2196f3","#f44336"));
        themes.add(new ThemeColor("Bright Vault","#00d2ff","#928DAB"));
        themes.add(new ThemeColor("Solid Vault","#3a7bd5","#3a6073"));
        themes.add(new ThemeColor("Sunset","#0B486B","#F56217"));
        themes.add(new ThemeColor("Grapefruit Sunset","#e96443","#904e95"));
        themes.add(new ThemeColor("Deep Sea Space","#2C3E50","#4CA1AF"));
        themes.add(new ThemeColor("Dusk","#2C3E50","#FD746C"));
        themes.add(new ThemeColor("Minimal Red","#F00000","#DC281E"));
        themes.add(new ThemeColor("Royal","#141E30","#243B55"));
        themes.add(new ThemeColor("Mauve","#42275a","#734b6d"));
        themes.add(new ThemeColor("Frost","#000428","#004e92"));
        themes.add(new ThemeColor("Lush","#56ab2f","#a8e063"));
        themes.add(new ThemeColor("Firewatch","#cb2d3e","#ef473a"));
        themes.add(new ThemeColor("Sherbert","#f79d00","#64f38c"));
        themes.add(new ThemeColor("Blood Red","#f85032","#e73827"));
        themes.add(new ThemeColor("Sun on the Horizon","#fceabb","#f8b500"));
        themes.add(new ThemeColor("IIIT Delhi","#808080","#3fada8"));
        themes.add(new ThemeColor("Dusk","#ffd89b","#19547b"));
        themes.add(new ThemeColor("50 Shades of Grey","#bdc3c7","#2c3e50"));
        themes.add(new ThemeColor("Dania","#BE93C5","#7BC6CC"));
        themes.add(new ThemeColor("Limeade","#A1FFCE","#FAFFD1"));
        themes.add(new ThemeColor("Disco","#4ECDC4","#556270"));
        themes.add(new ThemeColor("Love Couple","#3a6186","#89253e"));
        themes.add(new ThemeColor("Azure Pop","#ef32d9","#89fffd"));
        themes.add(new ThemeColor("Nepal","#de6161","#2657eb"));
        themes.add(new ThemeColor("Cosmic Fusion","#ff00cc","#333399"));
        themes.add(new ThemeColor("Brady Brady Fun Fun","#00c3ff","#ffff1c"));
        themes.add(new ThemeColor("Black Rosé","#f4c4f3","#fc67fa"));
        ThemeColor[] themeColors = new ThemeColor[themes.size()];
        themeColors = themes.toArray(themeColors);
        return themeColors;
    }

    public int ColorList(int numOfColors) {
        ArrayList<Integer> colorList = new ArrayList<Integer>();

            boolean similarFound = false;
            for(int color : colorList){
                if(similarTo(color)){
                    similarFound = true;
                    break;
                }
            }
            if(!similarFound){
                return 0;
            }
        return 0;

    }

    public boolean similarTo(int color){
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        double distance = (r - this.r)*(r - this.r) + (g - this.g)*(g - this.g) + (b - this.b)*(b - this.b);
        if(distance < 5000){
            return true;
        }else{
            return false;
        }
    }

}

