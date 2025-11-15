import React from "react";
import { useState, useEffect } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { Box, Typography } from "@mui/material";
import { BASE_API_URL, ENDPOINTS } from '../data/constants';

export default function EmailVerificationPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const [verificationStatus, setVerificationStatus] = useState("Verifying...");

  useEffect(() => {
    const verifyUser = async () => {
      try {
        const token = searchParams.get("token");
        const response = await fetch(
          `${BASE_API_URL}${ENDPOINTS.VERIFY_EMAIL}?token=${token}`,
          {
            method: "GET",
            // GET - no body; keep headers minimal
            headers: {
              Accept: "application/json",
            },
          }
        );
        if (response.ok) {
          // Prefer parsing response body if JSON, but not required for success
          try {
            const data = await response.json();
            if (data && data.success) {
              setVerificationStatus("User verified successfully! Redirecting to login...");
            } else {
              setVerificationStatus("User verified successfully! Redirecting to login...");
            }
          } catch (e) {
            // Non-JSON response on success - still treat as success
            setVerificationStatus("User verified successfully! Redirecting to login...");
          }

          // Redirect to login after a short delay
          setTimeout(() => navigate("/login"), 2000);
        } else {
          // Try to parse JSON error body, but handle non-JSON gracefully
          try {
            const data = await response.json();
            setVerificationStatus(data.errorMessage || "Verification failed.");
          } catch (e) {
            setVerificationStatus("Verification failed. Please try again.");
          }
        }
      } catch (err) {
        console.log(err);
        setVerificationStatus("Something went wrong. Please try again later.");
      }
    };
    verifyUser();
  }, [searchParams, navigate]);

  return (
    <Box>
      <Typography variant='h3' style={{ margin: "1em 0 0 1em" }}>
        {verificationStatus}
      </Typography>
    </Box>
  );
}
