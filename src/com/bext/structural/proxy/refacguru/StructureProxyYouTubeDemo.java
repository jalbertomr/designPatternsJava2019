package com.bext.structural.proxy.refacguru;

import java.util.*;

public class StructureProxyYouTubeDemo {
    public static void main(String... args) {
      ThirdPartyYouTube youTubeServiceA = new ThirdPartyYouTube();
      CachedYouTube proxyYouTube = new CachedYouTube( youTubeServiceA);
      YoutubeManager youtube = new YoutubeManager( proxyYouTube);
      youtube.reactOnUserInput();
    }

    interface ThirdPartyYoutubeLib {
        List<String> listVideos();
        String getVideoInfo(String id);
        void downloadVideo(String id);
        boolean existVideo(String id);
    }

    static class ThirdPartyYouTube implements ThirdPartyYoutubeLib {
        List<String> listVideos = new ArrayList<>(Arrays.asList("panteraRosa","rickAndMorty","DrWho"));
        Map<String, String> mapVideos = new HashMap<>();

        public ThirdPartyYouTube() {
            mapVideos.put( listVideos.get(0), "video de la pantera Rosa ....");
            mapVideos.put( listVideos.get(1), "video de Rick and Morty...");
            mapVideos.put( listVideos.get(2), "video de Dr Who...");
        }

        @Override
        public List<String> listVideos() {
            return this.listVideos;
        }

        @Override
        public String getVideoInfo(String id) {
            return mapVideos.get(id);
        }

        @Override
        public void downloadVideo(String id) {
            System.out.println("downloading..." + id + mapVideos.get( id));
        }
        @Override
        public boolean existVideo(String id) {
            return mapVideos.containsKey(id);
        }
    }

    static class CachedYouTube implements ThirdPartyYoutubeLib {
        private ThirdPartyYoutubeLib service;
        private List<String> listCache;
        private String videoCache;
        private Boolean existCache;
        boolean needReset;

        public CachedYouTube(ThirdPartyYoutubeLib service) {
            this.service = service;
        }

        @Override
        public List<String> listVideos() {
            if (listCache == null || needReset)
                listCache = service.listVideos();
            return listCache;
        }

        @Override
        public String getVideoInfo(String id) {
            if (videoCache == null || needReset)
                videoCache = service.getVideoInfo( id);
            return videoCache;
        }
        @Override
        public boolean existVideo(String id) {
            if (existCache == null || needReset)
                existCache = service.existVideo( id);
              return existCache;
        }

        @Override
        public void downloadVideo(String id) {
            if (!existVideo(id) || needReset)
                service.downloadVideo( id);
        }
    }

    static class YoutubeManager {
        protected ThirdPartyYoutubeLib service;

        public YoutubeManager(ThirdPartyYoutubeLib service) {
            this.service = service;
        }

        public void renderVideoPage(String id){
            System.out.println( service.getVideoInfo(id));
        }

        public void renderListPanel() {
            System.out.println( service.listVideos().toString());
        }

        public void reactOnUserInput() {
            renderListPanel();
            System.out.println("type de a video id in list...");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();
            renderVideoPage( id);
        }
    }
}
