package adapterPattern;

public class Test {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "mp3 Music");
        audioPlayer.play("mp4", "mp4 Music");
        audioPlayer.play("mp5", "mp5 Music");
        audioPlayer.play("vlc", "vlc Music");
    }
}
