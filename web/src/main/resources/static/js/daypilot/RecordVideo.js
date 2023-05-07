let mediaRecorder;
let recordedBlobs;

const recordedVideo = document.querySelector('video#recorded');

const recordButton = document.querySelector('button#record');
const playButton = document.querySelector('button#play');
const downloadButton = document.querySelector('button#download');

recordButton.onclick = toggleRecording;
playButton.onclick = play;
downloadButton.onclick = download;


navigator.mediaDevices.getUserMedia({audio: true, video: true})
    .then((stream) => {
        recordButton.disabled = false;
        console.log('getUserMedia() got stream: ', stream);
        window.stream = stream;
    })
    .catch((error) => {
        console.log('navigator.getUserMedia error: ', error);
    });

function handleDataAvailable(event) {
    if (event.data && event.data.size > 0) {
        recordedBlobs.push(event.data);
    }
}

function handleStop(event) {
    console.log('Recorder stopped: ', event);
}

function toggleRecording() {
    if (recordButton.textContent === 'Начать запись') {
        startRecording();
    }
    else {
        stopRecording();
        recordButton.textContent = 'Начать запись';
        playButton.disabled = false;
        downloadButton.disabled = false;
    }
}


function startRecording() {
    recordedBlobs = [];
    try {
        mediaRecorder = new MediaRecorder(window.stream);
    } catch (e) {
        console.error('Exception while creating MediaRecorder: ' + e);
        return;
    }
    console.log('Created MediaRecorder', mediaRecorder);
    recordButton.textContent = 'Остановить запись';
    playButton.disabled = true;
    downloadButton.disabled = true;
    mediaRecorder.onstop = handleStop;
    mediaRecorder.ondataavailable = handleDataAvailable;
    mediaRecorder.start(10);
    console.log('MediaRecorder started', mediaRecorder);
}

function stopRecording() {
    mediaRecorder.stop();
    console.log('Recorded Blobs: ', recordedBlobs);
    recordedVideo.controls = true;
}

function play() {
    const superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
    recordedVideo.src = window.URL.createObjectURL(superBuffer);
}

function download() {
    const blob = new Blob(recordedBlobs, {type: 'video/webm'});
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.style.display = 'none';
    a.href = url;
    a.download = 'test.webm';
    document.body.appendChild(a);
    a.click();
    setTimeout(function () {
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
    }, 100);
}