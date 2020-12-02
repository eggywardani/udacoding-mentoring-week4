<?php
include("koneksi.php");


if (!empty($_GET['id'])) {
    $id = $_GET['id'];
    $query = "SELECT * FROM pengunjung where id = $id";
} else {
    $query = "SELECT * FROM pengunjung";
}


$get = mysqli_query($connect, $query);
$data = array();

if (mysqli_num_rows($get) > 0) {
    while ($row = mysqli_fetch_assoc($get)) {
        $data[] = $row;
    }
    setResponse(true, "Data ditemukan", $data);
} else {
    setResponse(false, "Data tidak ditemukan", $data);
}


function setResponse($isSuccess, $message, $data)
{
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,
        'data' => $data,

    );
    echo json_encode($result);
}
