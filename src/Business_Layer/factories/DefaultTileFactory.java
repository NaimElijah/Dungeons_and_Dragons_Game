package Business_Layer.factories;

import Business_Layer.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Default implementation used by the CLI loader.
 *
 * It owns the mapping between level-file symbols and runtime objects.
 */
public class DefaultTileFactory implements TileFactory {

    @FunctionalInterface
    private interface TileCreator {
        Tile create(int x, int y);
    }

    private final Map<Character, TileCreator> registry = new HashMap<>();
    private final int playerChoice;
    private final List<Monster> monsters;
    private final List<Trap> traps;
    private final Consumer<String> messageCallback;
    private final Consumer<Player> playerSink;

    public DefaultTileFactory(
            int playerChoice,
            List<Monster> monsters,
            List<Trap> traps,
            Consumer<String> messageCallback,
            Consumer<Player> playerSink
    ) {
        this.playerChoice = playerChoice;
        this.monsters = monsters;
        this.traps = traps;
        this.messageCallback = messageCallback;
        this.playerSink = playerSink;

        registerDefaults();
    }

    @Override
    public Tile createTile(char symbol, int x, int y) {
        final TileCreator creator = registry.get(symbol);
        if (creator != null) {
            return creator.create(x, y);
        }

        // Defensive default: treat unknown symbols as empty.
        return new Empty(x, y);
    }

    private void registerDefaults() {
        registry.put('#', Wall::new);
        registry.put('.', Empty::new);

        // Monsters
        registerMonster('s', (x, y) -> new Monster(3, "s", x, y, "Lannister Solider", 80, 25, 3, 25));
        registerMonster('k', (x, y) -> new Monster(4, "k", x, y, "Lannister Knight", 200, 14, 8, 50));
        registerMonster('q', (x, y) -> new Monster(5, "q", x, y, "Queen's Guard", 400, 20, 15, 100));
        registerMonster('z', (x, y) -> new Monster(3, "z", x, y, "Wright", 600, 30, 15, 100));
        registerMonster('b', (x, y) -> new Monster(4, "b", x, y, "Bear-Wright", 1000, 75, 30, 250));
        registerMonster('g', (x, y) -> new Monster(5, "g", x, y, "Giant-Wright", 1500, 100, 40, 500));
        registerMonster('w', (x, y) -> new Monster(6, "w", x, y, "White Walker", 2000, 150, 50, 1000));
        registerMonster('M', (x, y) -> new Boss(6, "M", x, y, "The Mountain", 1000, 60, 25, 500, 5));
        registerMonster('C', (x, y) -> new Boss(1, "C", x, y, "Queen Cersei", 100, 10, 10, 1000, 8));
        registerMonster('K', (x, y) -> new Boss(8, "K", x, y, "Night's King", 5000, 300, 150, 5000, 3));

        // Traps
        registerTrap('B', (x, y) -> new Trap(1, 5, "B", x, y, "Bonus Trap", 1, 1, 1, 250));
        registerTrap('Q', (x, y) -> new Trap(3, 7, "Q", x, y, "Queen's Trap", 250, 50, 10, 100));
        registerTrap('D', (x, y) -> new Trap(1, 10, "D", x, y, "Death Trap", 500, 100, 20, 250));

        // Player spawn
        registry.put('@', this::createPlayer);
    }

    private void registerMonster(char symbol, TileCreator creator) {
        registry.put(symbol, (x, y) -> {
            final Tile tile = creator.create(x, y);
            if (tile instanceof Monster) {
                final Monster m = (Monster) tile;
                m.initialize_messages(messageCallback::accept);
                monsters.add(m);
            }
            return tile;
        });
    }

    private void registerTrap(char symbol, TileCreator creator) {
        registry.put(symbol, (x, y) -> {
            final Tile tile = creator.create(x, y);
            if (tile instanceof Trap) {
                final Trap t = (Trap) tile;
                t.initialize_messages(messageCallback::accept);
                traps.add(t);
            }
            return tile;
        });
    }

    private Tile createPlayer(int x, int y) {
        final Player p = DefaultPlayerFactory.create(playerChoice, x, y);
        if (p != null) {
            p.initialize_messages(messageCallback::accept);
            playerSink.accept(p);
            return p;
        }
        // If the user picked an invalid player index, still keep the board loadable.
        return new Empty(x, y);
    }
}
