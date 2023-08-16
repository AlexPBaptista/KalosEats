<?php
session_start();
$db = mysqli_connect('localhost', 'alexandre', 'password123', 'kaloseats');

$pnome = $unome = $email = $dnac = $telefonedono = $telemovel = $nomeestabelecimento = $morada =
    $horarioabertura = $horarioencerro = $telefoneestabelecimento = $website = $cafe = $bar = $restaurante = $tipo = "";
$virgula = ', ';
$espaco = " ";
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty($_POST["pnomedono"])) {
        $pnomeErr = "Primeiro Nome é requerido";
    } else {
        $pnome = test_input($_POST["pnomedono"]);
    }
    if (empty($_POST["unomedono"])) {
        $unomeErr = "Últimos Nome é requerido";
    } else {
        $unome = test_input($_POST["unomedono"]);
    }
    if (empty($_POST["email"])) {
        $emailErr = "Email é requerido";
    } else {
        $email = test_input($_POST["email"]);
    }
    $dnac = test_input($_POST["dnac"]);
    $cafe = test_input($_POST["cafe"]);
    $bar = test_input($_POST["bar"]);
    $restaurante = test_input($_POST["restaurante"]);
    $telefonedono = test_input($_POST["telefonedono"]);
    if (empty($_POST["telemovel"])) {
        $telemovelErr = "Telemóvel é requerido";
    } else {
        $telemovel = test_input($_POST["telemovel"]);
    }
    if (empty($_POST["nomeestabelecimento"])) {
        $nomeestabelecimentoErr = "Nome de estabelecimento é requerido";
    } else {
        $nomeestabelecimento = test_input($_POST["nomeestabelecimento"]);
    }
    if (empty($_POST["morada"])) {
        $moradaErr = "Morada é requerido";
    } else {
        $morada = test_input($_POST["morada"]);
    }
    if (empty($_POST["horarioabertura"])) {
        $horarioaberturaErr = "Horário de abertura é requerido";
    } else {
        $horarioabertura = test_input($_POST["horarioabertura"]);
    }
    if (empty($_POST["horarioencerro"])) {
        $horarioencerroErr = "Horário de encerro é requerido";
    } else {
        $horarioencerro = test_input($_POST["horarioencerro"]);
    }
    $telefoneestabelecimento = test_input($_POST["telefoneestabelecimento"]);
    $website = test_input($_POST["website"]);
    if ($cafe != "") {
        $tipo .= $cafe;
        $tipo .= $espaco;
    }
    if ($bar != "") {
        $tipo .= $bar;
        $tipo .= $espaco;
    }
    if ($restaurante != "") {
        $tipo .= $restaurante;
        $tipo .= $espaco;
    }

    $querydono = "INSERT INTO dono (primeiroNome, ultimoNome, email, dataNascimento, telefoneDono, telemovel) VALUES 
        ('$pnome', '$unome','$email', '$dnac', '$telefonedono', '$telemovel')";
    $queryestabelecimento = "INSERT INTO estabelecimentos (tipo, nomeEstabelecimento, morada, horaAbertura, horaEncerro, telefone, website) 
        VALUES ('$tipo','$nomeestabelecimento', '$morada', '$horarioabertura', '$horarioencerro', '$telefoneestabelecimento', '$website')";
    mysqli_query($db, $queryestabelecimento);
    mysqli_query($db, $querydono);
} else {
    echo $_SERVER["REQUEST_METHOD"];
}
ini_set('display_errors', 0);
ini_set('display_startup_errors', 0);
include "index.html";

function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
