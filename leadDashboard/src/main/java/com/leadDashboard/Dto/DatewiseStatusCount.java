package com.leadDashboard.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Accessors(chain=true)
@Data
@RequiredArgsConstructor
public class DatewiseStatusCount {
	private long leadGenerated;
	private long notALead;
	private long firstContactEstablishedNoResponse;
	private long firstContactEstablishedRejected;
	private long firstContactEstablishedInformationRequested;
	private long firstContactEstablishedToBeContactedAtFutureDate;
	private long firstContactEstablishedSchedulingDetailsReceived;
	private long firstContactEstablishedMeetingScheduled;
	private long firstContactEstablishedMeetingScheduledInitialPitchPrepared;
	private long firstContactEstablishedMeetingScheduledInitialPitchUnderRevision;
	private long firstContactEstablishedMeetingScheduledInitialPitchApprovedByBusinessHead;
	private long firstContactEstablishedNoResponseEmailFollowUp;
	private long firstContactEstablishedNoResponseClosed;
	private long initialPitchMadeResponseAwaited;
	private long initialPitchMadeResponseAwaitedEmailFollowUp;
	private long initialPitchMadeRejectedByClient;
	private long initialPitchMadeReworkRequestedByClient;
	private long initialPitchMadeReworkUnderProgress;
	private long initialPitchMadeReworkCompletedToBeApprovedByBusinessHead;
	private long initialPitchMadeReworkApproved;
	private long initialPitchMadeAfterRework;
	private long businessProposalRequestedByClient;
	private long businessProposalPrepared;
	private long businessProposalApproved;
	private long businessProposalUnderRevision;
	private long businessProposalSentResponseAwaited;
	private long businessProposalSentRejectedByClient;
	private long businessProposalSentDeferredByClient;
	private long businessProposalSentUnderNegotiationWithClient;
	private long businessProposalAcceptedByClient;
	private long draftAgreementPrepared;
	private long draftAgreementUnderRevision;
	private long draftAgreementApprovedByBusinessHead;
	private long draftAgreementSharedResponseAwaited;
	private long draftAgreementSharedRejectedByClient;
	private long draftAgreementSharedUnderNegotiationWithClient;
	private long draftAgreementSharedDeferredByClient;
	private long draftAgreementSharedAcceptedByClient;
	private long finalAgreementPrintedAndSharedWithClient;
	private long finalAgreementSignedByClient;
	private long finalAgreementSignedPrintedCopyReceivedAndStored;
	public DatewiseStatusCount(long leadGenerated, long notALead, long firstContactEstablishedNoResponse,
			long firstContactEstablishedRejected, long firstContactEstablishedInformationRequested,
			long firstContactEstablishedToBeContactedAtFutureDate,
			long firstContactEstablishedSchedulingDetailsReceived, long firstContactEstablishedMeetingScheduled,
			long firstContactEstablishedMeetingScheduledInitialPitchPrepared,
			long firstContactEstablishedMeetingScheduledInitialPitchUnderRevision,
			long firstContactEstablishedMeetingScheduledInitialPitchApprovedByBusinessHead,
			long firstContactEstablishedNoResponseEmailFollowUp, long firstContactEstablishedNoResponseClosed,
			long initialPitchMadeResponseAwaited, long initialPitchMadeResponseAwaitedEmailFollowUp,
			long initialPitchMadeRejectedByClient, long initialPitchMadeReworkRequestedByClient,
			long initialPitchMadeReworkUnderProgress, long initialPitchMadeReworkCompletedToBeApprovedByBusinessHead,
			long initialPitchMadeReworkApproved, long initialPitchMadeAfterRework,
			long businessProposalRequestedByClient, long businessProposalPrepared, long businessProposalApproved,
			long businessProposalUnderRevision, long businessProposalSentResponseAwaited,
			long businessProposalSentRejectedByClient, long businessProposalSentDeferredByClient,
			long businessProposalSentUnderNegotiationWithClient, long businessProposalAcceptedByClient,
			long draftAgreementPrepared, long draftAgreementUnderRevision, long draftAgreementApprovedByBusinessHead,
			long draftAgreementSharedResponseAwaited, long draftAgreementSharedRejectedByClient,
			long draftAgreementSharedUnderNegotiationWithClient, long draftAgreementSharedDeferredByClient,
			long draftAgreementSharedAcceptedByClient, long finalAgreementPrintedAndSharedWithClient,
			long finalAgreementSignedByClient, long finalAgreementSignedPrintedCopyReceivedAndStored) {
		super();
		this.leadGenerated = leadGenerated;
		this.notALead = notALead;
		this.firstContactEstablishedNoResponse = firstContactEstablishedNoResponse;
		this.firstContactEstablishedRejected = firstContactEstablishedRejected;
		this.firstContactEstablishedInformationRequested = firstContactEstablishedInformationRequested;
		this.firstContactEstablishedToBeContactedAtFutureDate = firstContactEstablishedToBeContactedAtFutureDate;
		this.firstContactEstablishedSchedulingDetailsReceived = firstContactEstablishedSchedulingDetailsReceived;
		this.firstContactEstablishedMeetingScheduled = firstContactEstablishedMeetingScheduled;
		this.firstContactEstablishedMeetingScheduledInitialPitchPrepared = firstContactEstablishedMeetingScheduledInitialPitchPrepared;
		this.firstContactEstablishedMeetingScheduledInitialPitchUnderRevision = firstContactEstablishedMeetingScheduledInitialPitchUnderRevision;
		this.firstContactEstablishedMeetingScheduledInitialPitchApprovedByBusinessHead = firstContactEstablishedMeetingScheduledInitialPitchApprovedByBusinessHead;
		this.firstContactEstablishedNoResponseEmailFollowUp = firstContactEstablishedNoResponseEmailFollowUp;
		this.firstContactEstablishedNoResponseClosed = firstContactEstablishedNoResponseClosed;
		this.initialPitchMadeResponseAwaited = initialPitchMadeResponseAwaited;
		this.initialPitchMadeResponseAwaitedEmailFollowUp = initialPitchMadeResponseAwaitedEmailFollowUp;
		this.initialPitchMadeRejectedByClient = initialPitchMadeRejectedByClient;
		this.initialPitchMadeReworkRequestedByClient = initialPitchMadeReworkRequestedByClient;
		this.initialPitchMadeReworkUnderProgress = initialPitchMadeReworkUnderProgress;
		this.initialPitchMadeReworkCompletedToBeApprovedByBusinessHead = initialPitchMadeReworkCompletedToBeApprovedByBusinessHead;
		this.initialPitchMadeReworkApproved = initialPitchMadeReworkApproved;
		this.initialPitchMadeAfterRework = initialPitchMadeAfterRework;
		this.businessProposalRequestedByClient = businessProposalRequestedByClient;
		this.businessProposalPrepared = businessProposalPrepared;
		this.businessProposalApproved = businessProposalApproved;
		this.businessProposalUnderRevision = businessProposalUnderRevision;
		this.businessProposalSentResponseAwaited = businessProposalSentResponseAwaited;
		this.businessProposalSentRejectedByClient = businessProposalSentRejectedByClient;
		this.businessProposalSentDeferredByClient = businessProposalSentDeferredByClient;
		this.businessProposalSentUnderNegotiationWithClient = businessProposalSentUnderNegotiationWithClient;
		this.businessProposalAcceptedByClient = businessProposalAcceptedByClient;
		this.draftAgreementPrepared = draftAgreementPrepared;
		this.draftAgreementUnderRevision = draftAgreementUnderRevision;
		this.draftAgreementApprovedByBusinessHead = draftAgreementApprovedByBusinessHead;
		this.draftAgreementSharedResponseAwaited = draftAgreementSharedResponseAwaited;
		this.draftAgreementSharedRejectedByClient = draftAgreementSharedRejectedByClient;
		this.draftAgreementSharedUnderNegotiationWithClient = draftAgreementSharedUnderNegotiationWithClient;
		this.draftAgreementSharedDeferredByClient = draftAgreementSharedDeferredByClient;
		this.draftAgreementSharedAcceptedByClient = draftAgreementSharedAcceptedByClient;
		this.finalAgreementPrintedAndSharedWithClient = finalAgreementPrintedAndSharedWithClient;
		this.finalAgreementSignedByClient = finalAgreementSignedByClient;
		this.finalAgreementSignedPrintedCopyReceivedAndStored = finalAgreementSignedPrintedCopyReceivedAndStored;
		
	}
	
	
	

	
	

	
	
	
}
