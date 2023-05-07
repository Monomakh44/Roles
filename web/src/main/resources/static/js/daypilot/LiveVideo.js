let webcamStream;

function startWebcam() {
    // запросить видео и аудио поток с веб-камеры пользователя
    navigator.mediaDevices.getUserMedia({
        audio: true,
        video: true
    }).then((stream) => {
        let video = document.querySelector('#video');
        video.srcObject = stream;
        video.play();

        webcamStream = stream;
    }).catch((error) => {
        console.log('navigator.getUserMedia error: ', error);
    });
}

function stopWebcam() {
    webcamStream.getTracks()[0].stop(); // audio
    webcamStream.getTracks()[1].stop(); // video
}
