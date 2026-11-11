package CaveDiver.src.CaveDiver.src;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundManager {
    private static Thread musicThread;
    private static volatile boolean running = true;

    public static void playMusicLoop(String resourcePath) {
        musicThread = new Thread(() -> {
            while (running) {
                try {
                    // Загружаем файл из classpath, а не с диска
                    InputStream is = SoundManager.class.getResourceAsStream(resourcePath);
                    if (is == null) {
                        System.err.println("Музыка не найдена: " + resourcePath);
                        return;
                    }
                    BufferedInputStream bis = new BufferedInputStream(is);
                    AdvancedPlayer player = new AdvancedPlayer(bis);
                    
                    // Играем трек от начала до конца
                    player.play(0, Integer.MAX_VALUE);
                    
                } catch (Exception e) {
                    System.err.println("Ошибка музыки: " + e.getMessage());
                    break;
                }
            }
        });
        musicThread.setDaemon(true); // Поток завершится вместе с игрой
        musicThread.start();
    }

    public static void stopMusic() {
        running = false;
        if (musicThread != null) {
            musicThread.interrupt();
        }
    }
}