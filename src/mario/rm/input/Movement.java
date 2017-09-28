package mario.rm.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mario.rm.SuperMario;
import mario.rm.handler.Handler;
import mario.rm.sprite.Sprite;

/**
 *
 * @author LENOVO
 */
public class Movement implements KeyListener { //RESPONSABILE DEL MOVIMENTO

    private int velX;  //COSTANTE PER LA VELOCITA' SULL'ASSE X
    private double jump; //COSTANTE PER LA DISTANZA DI SALTO

    private static final Sound salto = new Sound("mario/res/Sound/nsmb_jump.wav");

    private final boolean move[];

    private Handler handler;

    public Movement(int velX, double jump, Handler handler) {
        this.velX = velX;
        this.jump = jump;
        move = new boolean[]{false, false};
        this.handler = handler;
        System.out.println("JUMP: " + jump);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    } //DA VERIFICARE IL FUNZIONAMENTO

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();   //NON STRETTAMENTE NECCESSARIO
        for (Sprite sp : handler.getPlayer()) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:  //SE VIENE PREMUTO IL TATO SINISTRO
                    if (!sp.isFalling()) {   //SE STA TOCCANDO IL TEERRENO
                        sp.setDirezione(-1);    //IMPOSTO A STA CORRENDO SPECCHIATO
                    } else {
                        sp.setDirezione(-30);   //SALTO SPECCHIATO
                    }
                    sp.setVelX(-velX);   //LA VELOCITA X  = -5
                    move[0] = true;
                    sp.setWalking(true);  //STA CAMMINANDO
                    break;
                case KeyEvent.VK_RIGHT: //SE VIENE PREMUTO IL TASTO DESTRO
                    if (!sp.isFalling()) {   //SE STA TOCCANDO IL PAVIMENTO
                        sp.setDirezione(1); //IMPOSTO A STA' CORRENDO
                    } else {
                        sp.setDirezione(30);    //IMPOSTO A SALTO NORMALE
                    }
                    sp.setVelX(velX);    //VELOCITA X = 5
                    move[1] = true;
                    sp.setWalking(true);  //STA CAMMINANDO
                    break;
                case KeyEvent.VK_UP:
                    if (!sp.isJumping() && !sp.isFalling()) {    //SE NON STAVA GIA SALTANDO PRIMA
                        if (salto.getCurrentFrame() != 0) {
                            salto.stop();
                        }
                        salto.start();
                        sp.setJumping(true);  //ORA STA SALTANDO
                        sp.setFalling(false);   //IMPOSTO A NON STA' SALTANDO
                        sp.setGravity(jump); //LA GRAVITA E' UGUALE A 7
                        sp.setDirezione((sp.getDirezione() / Math.abs(sp.getDirezione())) * 30);    //IMPOSTO IL SALTO NELLA DIREZIONE IN CUI ERA PRIMA
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!sp.isFalling()) {
                        sp.setTeleport();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();   //PALESE
        for (Sprite sp : handler.getPlayer()) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:  //SE VIENE PREMUTO IL TATO SINISTRO
                    move[0] = false;
                    if (!move[0] && !move[1]) {
                        sp.setWalking(false); //NON STA PIU CAMMINANDO
                    }
                    break;
                case KeyEvent.VK_RIGHT: //SE VIENE PREMUTO IL TASTO DESTRO
                    move[1] = false;
                    if (!move[0] && !move[1]) {
                        sp.setWalking(false);
                    }
                    ; //NON STA PIU CAMMINANDO
                    break;
                case KeyEvent.VK_UP:
                    if (sp.isJumping() && sp.getGravity() <= 1) {
                        sp.setFalling(true);  //STA CADENDO
                        sp.setGravity(-(jump / 3)); //FACCIO IN MODO CHE NON SI FERMI BRUSCAMENTE
                    } else if (sp.isJumping()) {
                        sp.setFalling(true);  //STA CADENDO
                        sp.setGravity(0);   //LO FERMO
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    SuperMario.addOption();
                    break;
                default:
                    break;
            }
        }
    }

}
