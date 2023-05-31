const token = 'y0_AgAAAAAWNTqjAAnfawAAAADiqxp7XU4LkysWRiij2fxyiGIX_O9cIaY';
let mediaRecorder;
let recordedBlobs;
let urlFile;
let ret;
let rit;
/*const recordedVideo = document.querySelector('video#recorded');*/

/*const recordButton = document.querySelector('button#record');
const playButton = document.querySelector('button#play');
const downloadButton = document.querySelector('button#download');*/

/*recordButton.onclick = startRecording;
playButton.onclick = play;
downloadButton.onclick = download;*/


navigator.mediaDevices.getUserMedia({audio: true, video: true})
    .then((stream) => {
        /*recordButton.disabled = false;*/
        console.log('getUserMedia() got stream: ', stream);
        window.stream = stream;
        startRecording();
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

/*function toggleRecording() {
    if (recordButton.textContent === 'Начать запись') {
        startRecording();
    }
    else {
        stopRecording();
        recordButton.textContent = 'Начать запись';
        /!*playButton.disabled = false;
        downloadButton.disabled = false;*!/
    }
}*/

function startRecording() {
    recordedBlobs = [];
    try {
        mediaRecorder = new MediaRecorder(window.stream);
    } catch (e) {
        console.error('Exception while creating MediaRecorder: ' + e);
        return;
    }
    console.log('Created MediaRecorder', mediaRecorder);
    /*recordButton.textContent = 'Остановить запись';
    playButton.disabled = true;
    downloadButton.disabled = true;*/
    mediaRecorder.onstop = handleStop;
    mediaRecorder.ondataavailable = handleDataAvailable;
    mediaRecorder.start(10);
    console.log('MediaRecorder started', mediaRecorder);
    setTimeout( stopRecording, /*1800000*/30000);
}
function stopRecording() {
    mediaRecorder.stop();
    console.log('Recorded Blobs: ', recordedBlobs);
    download();
    startRecording();
    /*recordedVideo.controls = true;*/
}
/*function play() {
    const superBuffer = new Blob(recordedBlobs, {type: 'video/mp4'});
    recordedVideo.src = window.URL.createObjectURL(superBuffer);
}*/

async function download()
{
    const dateNow = new Date();
    const fileName = `video_${dateNow.getDate()}_${dateNow.getMonth()+1}_${dateNow.getFullYear()}_${dateNow.getHours()}_${dateNow.getMinutes()}_${dateNow.getSeconds()}`
    const blob = new Blob(recordedBlobs, {type: 'video/mp4'});
    /*const fileUrl = window.URL.createObjectURL(blob);
     const a = document.createElement('a');
     a.style.display = 'none';
     a.href = url;
     a.download = 'test.mp4';
     document.body.appendChild(a);
     a.click();
     setTimeout(function () {
         document.body.removeChild(a);
         window.URL.revokeObjectURL(url);
     }, 100);*/
    await fetch(`https://cloud-api.yandex.net/v1/disk/resources/upload?path=/${fileName}.mp4&overwrite=true`, {
        method: 'GET',
        headers: {Authorization: `OAuth ${token}`},
    })
        .then((response)=>response.json())
        .then((data)=> {
            let ret = JSON.stringify(data);
            let rit = JSON.parse(ret);
            urlFile = rit.href;
        })
    await fetch(urlFile, {
        method: 'PUT',
        body: blob,
    })
    await fetch(`https://cloud-api.yandex.net/v1/disk/resources/download?path=/${fileName}.mp4&overwrite=true`,{
        method: 'GET',
        headers: {Authorization: `OAuth ${token}`},
    }).then((response)=>response.json())
        .then((data)=> {
            ret = JSON.stringify(data);
            rit = JSON.parse(ret);
            window.urlDownload=rit.href;
            const urlDown = urlDownload;
            $.ajax({
                type: "POST",
                url: "/recordVideo",
                data: { myUrl : urlDown },
            });
        })
}
