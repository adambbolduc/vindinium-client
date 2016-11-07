package com.brianstempin.vindiniumclient.bot.mybots;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMap {
    private static String pattern = "(##|[@$][\\d-]|  |\\[\\])";
    private static Pattern regex = Pattern.compile(pattern);
    private static TileFactory tileFactory = new TileFactory();

    private List<List <Tile> > tiles;

    public GameMap(int size, String map_str) {
        Matcher matcher = regex.matcher(map_str);

        List<Tile> tiles = new ArrayList<>();
        this.tiles = new ArrayList<>();
        while(matcher.find()) {
            tiles.add(tileFactory.makeFromString(matcher.group(0)));
        }

        for (int i = 0; i < size; i++) {
            this.tiles.add(tiles.subList(size * i, size * (i+1) -1));
        }
    }

    public Tile getTile(int i, int j) {
        return tiles.get(i-1).get(j-1);
    }

    @Override
    public String toString() {
        return this.tiles.toString();
    }

    public static class TileFactory {
        public Tile makeFromString(String string) {
            switch (string.charAt(0)) {
                case '#' :
                    return new ImpassableWoodTile();
                case '@':
                    return new HeroTile(Character.getNumericValue(string.charAt(1)));
                case '[':
                    return new TavernTile();
                case '$':
                    if (string.charAt(1) == '-') {
                        return new GoldMineTile(0);
                    } else {
                        return new GoldMineTile(Character.getNumericValue(string.charAt(1)));
                    }
                case ' ':
                    return new FreeTile();
                default:
                    throw new RuntimeException(string);
            }
        }
    }

    public interface Tile{
        boolean isPassable();
        boolean isHero();
        boolean isTavern();
        boolean isGoldMine();
        int belongsTo();
    }

    public static class ImpassableWoodTile implements Tile {

        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public boolean isHero() {
            return false;
        }

        @Override
        public boolean isTavern() {
            return false;
        }

        @Override
        public boolean isGoldMine() {
            return false;
        }

        @Override
        public int belongsTo() {
            return 0;
        }

        @Override
        public String toString() {
            return "##";
        }
    }

    public static class HeroTile implements Tile {
        private final int belongingTo;

        public HeroTile(int belongingTo) {
            this.belongingTo = belongingTo;
        }

        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public boolean isHero() {
            return true;
        }

        @Override
        public boolean isTavern() {
            return false;
        }

        @Override
        public boolean isGoldMine() {
            return false;
        }

        @Override
        public int belongsTo() {
            return belongingTo;
        }

        @Override
        public String toString() {
            return "@"+belongingTo;
        }
    }

    public static class TavernTile implements Tile {
        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public boolean isHero() {
            return false;
        }

        @Override
        public boolean isTavern() {
            return true;
        }

        @Override
        public boolean isGoldMine() {
            return false;
        }

        @Override
        public int belongsTo() {
            return 0;
        }

        @Override
        public String toString() {
            return "[]";
        }
    }

    public static class GoldMineTile implements Tile {
        private final int belongingTo;

        public GoldMineTile(int belongingTo) {
            this.belongingTo = belongingTo;
        }

        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public boolean isHero() {
            return false;
        }

        @Override
        public boolean isTavern() {
            return false;
        }

        @Override
        public boolean isGoldMine() {
            return true;
        }

        @Override
        public int belongsTo() {
            return belongingTo;
        }

        @Override
        public String toString() {
            return "$"+belongingTo;
        }
    }

    public static class FreeTile implements Tile {

        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public boolean isHero() {
            return false;
        }

        @Override
        public boolean isTavern() {
            return false;
        }

        @Override
        public boolean isGoldMine() {
            return false;
        }

        @Override
        public int belongsTo() {
            return 0;
        }

        @Override
        public String toString() {
            return "  ";
        }
    }
}
