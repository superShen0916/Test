package test.adapterPattern;

public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("mp3")) {
            System.out.println("Playing mp3 file:" + fileName);
        } else if (audioType.equals("mp4") || audioType.equals("vlc")) {
            MediaPlayer mediaPlayer = new MediaAdapter(audioType);
            mediaPlayer.play(audioType, fileName);
        } else {
            System.out.println("Invalid media:" + audioType);
        }
    }

}
