package mario.rm.sprite.tiles;

import java.util.ArrayList;
import mario.rm.Animation.Tile;
import mario.rm.handler.Handler;
import mario.rm.identifier.TilePart;
import mario.rm.identifier.Type;

/**
 *
 * @author LENOVO
 */
/**
 *
 * @return CLASSE CHE INDICA UN CHECKPOINT
 */
public class Checkpoint extends Tiles {

    public Checkpoint(int x, int y, int width, int height, Handler handler, Type type, ArrayList<Tile> anim, boolean collide, String part) {
        super(x, y, width, height, handler, type, anim, collide, part);
        width = 200;
        height = 200;
        breakable = false;
    }

    /**
     *
     * @return FUNZIONE USATA NEGL CHECKPOINT O PER LA BANDIERA DI FINE LIVELLO
     */
    @Override
    public void unlockable() {
        type = Type.CHECKPOINTSAFE;

        ArrayList<Tile> tempo = handler.getMemoria().getUnlockable();
        for (Tile tile1 : tempo) {
            if(tile1.getType() == type)
                temp = tile1.getImage(TilePart.valueOf(type.name()));
        }
    }

    @Override
    public void tick() {
    }

}
