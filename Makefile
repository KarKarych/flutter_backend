do_hust:
        docker build -t wvolfff/flutter_backend:latest .
        docker push wvolfff/flutter_backend:latest
jeronimo:
        docker pull wvolfff/flutter_backend:latest
        docker compose stop flutter_backend
        docker compose up -d
        docker logs flutter_backend -f
print:
        clear && docker logs flutter_backend -f
