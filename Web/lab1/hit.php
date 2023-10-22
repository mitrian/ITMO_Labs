<?php
    session_start();
    header("Access-Control-Allow-Origin: *");
    if (!isset($_SESSION["results"])) 
        $_SESSION["results"] = array();
    
    if (array_key_exists("t", $_GET) && $_GET['t']==1) {
        $_SESSION["results"] = array();
    } else {
        $t = microtime(true);
        if (array_key_exists("x", $_GET) && array_key_exists("y", $_GET) && array_key_exists("r", $_GET)) {
            $X = $_GET["x"];
            $Y = $_GET["y"];
            $R = $_GET["r"];
            if ($X >= -5 && $X <= 5 && in_array($Y, array(-4, -3, -2, -1, 0, 1, 2, 3, 4)) && in_array($R, array(1, 1.5, 2, 2.5, 3))) {
                if ((($Y <= $X + $R) && ($Y>=0) && ($X<=0)) ||(($X<=0) && ($X>=-($R)/2) && ($Y<=0) && ($Y>=-$R)) || (($X>=0) && ($Y<=0) && ((pow($X,2)+pow($Y,2)) <= pow($R,2)))) {
                    $res = '<tr><td>'.$X.'</td><td>'.$Y.'</td><td>'.$R.'</td><td>'.date('Y.m.d H:i:s').'</td><td>'.number_format((-$t + microtime(true))*1000000,3).' мс'.'</td><td>Попал</td></tr>';
                } else {
                    $res = '<tr><td>'.$X.'</td><td>'.$Y.'</td><td>'.$R.'</td><td>'.date('Y.m.d H:i:s').'</td><td>'.number_format((-$t + microtime(true))*1000000,3).' мс'.'</td><td>Мимо</td></tr>';
                }
                array_push($_SESSION["results"], $res);
                $response = '';
                foreach ($_SESSION["results"] as &$i) {
                    $response .= $i;
                }
                echo $response;
            } else echo -1;

        } else echo -1;
    }