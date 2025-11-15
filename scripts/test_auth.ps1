$base = 'http://localhost:8090/api'
$register = "$base/auth/register"
$login = "$base/auth/login"

# Change email if you prefer. Using + alias to allow multiple runs if your mailbox supports it.
$email = 'test.user+1@example.com'
$password = 'Password123'

$regBody = @{
    firstName = 'Test'
    lastName  = 'User'
    email     = $email
    password  = $password
} | ConvertTo-Json

Write-Host "Registering user $email..."
try {
    $regResp = Invoke-RestMethod -Uri $register -Method Post -Body $regBody -ContentType 'application/json' -ErrorAction Stop
    Write-Host "Register response:`n" ($regResp | ConvertTo-Json -Depth 5)
} catch {
    Write-Host "Register failed:`n" $_.Exception.Message
    if ($_.Exception.Response) {
        $respStream = $_.Exception.Response.GetResponseStream()
        $reader = New-Object System.IO.StreamReader($respStream)
        $body = $reader.ReadToEnd()
        Write-Host "Response body:`n$body"
    }
}

Start-Sleep -Seconds 1

Write-Host "Attempting login for $email..."
$loginBody = @{
    email = $email
    password = $password
} | ConvertTo-Json
try {
    $loginResp = Invoke-RestMethod -Uri $login -Method Post -Body $loginBody -ContentType 'application/json' -ErrorAction Stop
    Write-Host "Login response:`n" ($loginResp | ConvertTo-Json -Depth 5)
} catch {
    Write-Host "Login failed:`n" $_.Exception.Message
    if ($_.Exception.Response) {
        $respStream = $_.Exception.Response.GetResponseStream()
        $reader = New-Object System.IO.StreamReader($respStream)
        $body = $reader.ReadToEnd()
        Write-Host "Response body:`n$body"
    }
}
