import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    enum VideoType {
        CLIP, PREVIEW, EPISODE
    }
    static class Video {
        public String title;
        public String url;
        public VideoType videoType;
        public Video(String title, String url, VideoType videoType) {
            this.title = title;
            this.url = url;
            this.videoType = videoType;
        }
    }
    static class Episode {
        public String episodeName;
        public int episodeNumber;
        List<Video> videos;

        public Episode(String episodeName, int episodeNumber,
                       List<Video> videos) {
            this.episodeName = episodeName;
            this.episodeNumber = episodeNumber;
            this.videos = videos;
        }
    }
    static class Season {
        public String seasonName;
        public int seasonNumber;
        List<Episode> episodes;
        public Season(String seasonName, int seasonNumber,
                      List<Episode> episodes) {
            this.seasonName = seasonName;
            this.seasonNumber = seasonNumber;
            this.episodes = episodes;
        }
    }
    public static void main(String[] args) {

        List<Season> seasons = new ArrayList<>();
        for (int k = 1; k < 6; k++) {
            List<Episode> episodes = new ArrayList<>();
            for (int j = 1; j < 6; j++) {
                List<Video> videos = new ArrayList<>();
                for (int i = 1; i < 6; i++) {
                    Video video = new Video("Video"+k+"x"+j+"x"+i, "url"+k+"x"+j+"x"+i, VideoType.EPISODE);
                    videos.add(video);
                }
                Episode episode = new Episode("Episode"+k+"x"+j, j, videos);
                episodes.add(episode);
            }
            Season season = new Season("Season"+k, k, episodes);
            seasons.add(season);
        }


        System.out.println("List of all episodes:");
        seasons
                .forEach(season -> season.episodes
                        .forEach(episode -> System.out.println(episode.episodeName)));




        System.out.println("\nList of all season's names:");
        seasons
                .forEach(season -> System.out.println(season.seasonName));






        System.out.println("\nList of all episode's numbers:");
        seasons
                .forEach(season -> season.episodes
                        .forEach(episode -> System.out.println(season.seasonNumber+""+episode.episodeNumber)));





        System.out.println("\nList of videos from even seasons:");
        seasons.stream()
                .filter(season -> season.seasonNumber % 2 == 0)
                .collect(Collectors.toList())
                .forEach(season -> season.episodes
                        .forEach(episode -> episode.videos
                                .forEach(video -> System.out.println(video.title))));




        
        System.out.println("\nList of video's URLs:");
        seasons
                .forEach(season -> season.episodes
                        .forEach(episode -> episode.videos
                                .forEach(video -> System.out.println(video.url))));
    }
}